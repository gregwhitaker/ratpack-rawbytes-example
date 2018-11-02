package ratpack.example.api.upload;

import io.netty.buffer.ByteBuf;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import ratpack.exec.Promise;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import java.io.IOException;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class UploadHandler implements Handler {

    @Override
    public void handle(Context ctx) throws Exception {
        ctx.getRequest().getBodyStream()
                .subscribe(new Subscriber<ByteBuf>() {
                    final String fileName = String.format("cat.jpeg", System.currentTimeMillis());

                    private Path path = FileSystems.getDefault().getPath(".", "build", fileName).toAbsolutePath();
                    private Subscription subscription;
                    private AsynchronousFileChannel out;
                    long written;

                    @Override
                    public void onSubscribe(Subscription s) {
                        subscription = s;
                        try {
                            this.out = AsynchronousFileChannel.open(
                                    path,
                                    StandardOpenOption.CREATE,
                                    StandardOpenOption.WRITE,
                                    StandardOpenOption.TRUNCATE_EXISTING);
                            subscription.request(1);
                        } catch (IOException e) {
                            subscription.cancel();
                            ctx.error(e);
                        }
                    }

                    @Override
                    public void onNext(ByteBuf byteBuf) {
                        Promise.async(down -> out.write(byteBuf.nioBuffer(), written, null, down.completionHandler())
                        ).onError(error -> {
                            byteBuf.release();
                            subscription.cancel();
                            out.close();
                            ctx.error(error);
                        }).then(bytesWritten -> {
                            byteBuf.release();
                            written += (Integer) bytesWritten;
                            subscription.request(1);
                        });
                    }

                    @Override
                    public void onError(Throwable t) {
                        ctx.error(t);
                        try {
                            out.close();
                        } catch (IOException ignore) {
                            // ignore
                        }
                    }

                    @Override
                    public void onComplete() {
                        try {
                            out.close();
                        } catch (IOException ignore) {
                            // ignore
                        }

                        ctx.getResponse().send();
                    }
                });
    }
}

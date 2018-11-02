# ratpack-rawbytes-example
[![Build Status](https://travis-ci.org/gregwhitaker/ratpack-rawbytes-example.svg?branch=master)](https://travis-ci.org/gregwhitaker/ratpack-rawbytes-example)

An example of uploading raw bytes with [Ratpack](https://ratpack.io).

## Running the Example
1. Run the following Gradle command to start the example application:

        ./gradlew clean build run
        
2. Run the following curl command to upload the `cat.jpeg`

        curl -T ./cat.jpeg \
        -H "Content-Type:image/jpeg" \
        http://localhost:5050/api/v1/upload
        
    If successful, you will now see that `cat.jpeg` has been uploaded to the `build` directory. Open the file to validate that it has been uploaded correctly. 

## Bugs and Feedback
For bugs, questions, and discussions please use the [Github Issues](https://github.com/gregwhitaker/ratpack-rawbytes-example/issues).

## License
MIT License

Copyright (c) 2018 Greg Whitaker

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
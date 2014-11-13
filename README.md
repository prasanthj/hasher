Hasher
======

This project contains Java implementation for the following fast non-cryptographic hash algorithms
- [Murmur2 32-bit variant] 
- [Murmur2 64-bit variant] 
- [Murmur3 32-bit variant] 
- [Murmur3 128-bit variant]
- [XXHash 64-bit variant]

## JMH Microbenchmark
### Running locally
To run the benchmark on your local machine
> git clone https://github.com/prasanthj/hasher.git

> cd hasher

> mvn clean install

To run all benchmarks

> java -jar target/benchmarks.jar

(OR) with customer iterations (wi - warmup iterations, i - iterations, f - fork)
> java -jar target/benchmarks.jar -wi 5 -i 10 -f 1

To run a specific benchmark
> java -jar target/benchmarks.jar BenchmarkXXHash_64 -wi 5 -i 10 -f 1

[Murmur2 32-bit variant]:https://github.com/prasanthj/hasher/blob/master/src/main/java/hasher/Murmur2_32.java 
[Murmur2 64-bit variant]:https://github.com/prasanthj/hasher/blob/master/src/main/java/hasher/Murmur2_64.java
[Murmur3 32-bit variant]:https://github.com/prasanthj/hasher/blob/master/src/main/java/hasher/Murmur3_32.java
[Murmur3 128-bit variant]:https://github.com/prasanthj/hasher/blob/master/src/main/java/hasher/Murmur3_128.java 
[XXHash 64-bit variant]:https://github.com/prasanthj/hasher/blob/master/src/main/java/hasher/XXHash_64.java
[here]:https://github.com/prasanthj/hasher/blob/master/src/main/java/benchmarks/results

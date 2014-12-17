Hasher ![Build Status](https://circleci.com/gh/prasanthj/hasher.png?circle-token=eaa12cece015c20ad9c5f9d0764e26338a4cb88a) 
======

This project contains Java implementation for the following fast non-cryptographic hash algorithms
- [Murmur2] - 32, 64-bit variants
- [Murmur3] - 32, 64, 128-bit variants
- [XXHash] - 64-bit variant
- [FNV1] - 32, 64-bit variants
- [FNV1A] - 32, 64-bit variants

## JMH Microbenchmark
### Running locally
To run the benchmark on your local machine
> git clone https://github.com/prasanthj/hasher.git

> cd hasher

> mvn clean install

To run all benchmarks

> java -jar target/benchmarks.jar

(OR) with custom iterations (wi - warmup iterations, i - iterations, f - fork)
> java -jar target/benchmarks.jar -wi 5 -i 10 -f 1

To run a specific benchmark
> java -jar target/benchmarks.jar Benchmark32BitHash -wi 5 -i 10 -f 1

### Results
_NOTE:_ Its always good to run the benchmarks on your local machine as YMMV.

Latest results can be found [here]. All results are generated with the following setup.

**Benchmark Setup**

- _Java version:_ 1.7.0_40 (64-Bit Server VM)
- _Operating system:_ Mac OS X v10.10 (Yosemite)
- _Processor:_ 2.7 GHz Intel Core i7
- _Memory:_ 16GB 1600 MHz DDR3
- _Storage:_ 500GB Flash Storage

## Issues
Bug fixes or improvements are welcome! Please fork the project and send pull request on github. Or report issues here https://github.com/prasanthj/hasher/issues


[Murmur2]:https://github.com/prasanthj/hasher/blob/master/src/main/java/hasher/Murmur2.java 
[Murmur3]:https://github.com/prasanthj/hasher/blob/master/src/main/java/hasher/Murmur3.java
[XXHash]:https://github.com/prasanthj/hasher/blob/master/src/main/java/hasher/XXHash.java
[FNV1]:https://github.com/prasanthj/hasher/blob/master/src/main/java/hasher/FNV1.java
[FNV1A]:https://github.com/prasanthj/hasher/blob/master/src/main/java/hasher/FNV1a.java
[here]:https://github.com/prasanthj/hasher/blob/master/src/main/java/benchmarks/results

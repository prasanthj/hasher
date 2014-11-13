/**
 *   Copyright 2014 Prasanth Jayachandran
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package benchmarks;

import com.google.common.hash.Hashing;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

import de.greenrobot.common.hash.Murmur3F;
import hasher.FNV1;
import hasher.FNV1a;
import hasher.Murmur2;
import hasher.Murmur3;
import hasher.XXHash;

/**
 *
 */
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(3)
@Warmup(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
public class Benchmark64BitHash {

  @Benchmark
  public long murmur2_64(BenchmarkData bd, ByteCounter bc) {
    byte[] bytes = bd.getBytes();
    bc.add(bytes.length);
    return Murmur2.hash64(bytes, bytes.length, 0);
  }

  @Benchmark
  public long guava_murmur3_64(BenchmarkData bd, ByteCounter bc) {
    byte[] bytes = bd.getBytes();
    bc.add(bytes.length);
    return Hashing.murmur3_128().hashBytes(bytes).asLong();
  }

  @Benchmark
  public long greenrobot_murmur3f_64(BenchmarkData bd, ByteCounter bc) {
    byte[] bytes = bd.getBytes();
    bc.add(bytes.length);
    Murmur3F hf = new Murmur3F();
    hf.update(bytes);
    return hf.getValue();
  }

  @Benchmark
  public long murmur3_64(BenchmarkData bd, ByteCounter bc) {
    byte[] bytes = bd.getBytes();
    bc.add(bytes.length);
    return Murmur3.hash64(bytes, bytes.length, 0);
  }

  @Benchmark
  public long fnv1_64(BenchmarkData bd, ByteCounter bc) {
    byte[] bytes = bd.getBytes();
    bc.add(bytes.length);
    return FNV1.hash64(bytes, bytes.length);
  }

  @Benchmark
  public long fnv1a_64(BenchmarkData bd, ByteCounter bc) {
    byte[] bytes = bd.getBytes();
    bc.add(bytes.length);
    return FNV1a.hash64(bytes, bytes.length);
  }

  @Benchmark
  public long xxhash_64(BenchmarkData bd, ByteCounter bc) {
    byte[] bytes = bd.getBytes();
    bc.add(bytes.length);
    return XXHash.hash64(bytes);
  }

  public static void main(String[] args) throws RunnerException {
    Options options = new OptionsBuilder()
        .include(Benchmark64BitHash.class.getSimpleName())
        .forks(1)
        .build();

    new Runner(options).run();
  }
}

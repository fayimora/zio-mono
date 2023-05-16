package com.fayi
package ziomono

import org.scalacheck.Prop.*

final class ExampleSuite extends TestSuite:
  test("hello world") {
    forAll { (int: Int, string: String) =>
      expectEquals(int, int)
      expectEquals(string, string)
    }
  }

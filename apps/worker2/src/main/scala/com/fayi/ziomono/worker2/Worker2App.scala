package com.fayi.ziomono.worker2

import zio.*
import zio.Console.*

object Worker2App extends ZIOAppDefault:
  def run =
    ZIO
      .log(s"Hello the time is ${java.time.LocalTime.now()} from worker 2")
      .repeat(Schedule.spaced(5.seconds))
      .forever

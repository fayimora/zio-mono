package com.fayi.ziomono.worker1

import zio.*
import zio.Console.*

object Main extends ZIOAppDefault:
  def run =
    ZIO
      .log(s"Hello the time is ${java.time.LocalTime.now()} from worker 1")
      .repeat(Schedule.spaced(5.seconds))
      .forever

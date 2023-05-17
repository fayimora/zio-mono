package com.fayi.ziomono

import zio.http.Server.Config
import zio.{ Scope, ZIO, ZIOAppArgs, ZIOAppDefault }
import zio.http.{ Handler, Server }

object HelloApp extends ZIOAppDefault:
  val app = Handler.text("Hello World!").toHttp

  override def run =
    Server.serve(app).provide(Server.defaultWithPort(8080))

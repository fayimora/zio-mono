package com.fayi.ziomono.api

import zio.*
import zio.http.*

object ApiApp extends ZIOAppDefault:
  val app = Http.collectZIO[Request] {
    case Method.GET -> root / "text" => ZIO.succeed(Response.text("Hello World!"))
    case Method.GET -> root / "json" =>
      ZIO.succeed(Response.json("""{"message": "Hello World!"}"""))
    case Method.GET -> root / "random" => Random.nextString(10).map(Response.text(_))
//    case req @ Method.POST -> root / "echo" => req.body.asString.map(Response.text(_))
    case Method.GET -> root => ZIO.succeed(Response.text("Ok"))
  }

  override def run =
    Server
      .serve(app)
      .flatMap(p => Console.printLine(s"Started server on port: $p"))
      .provide(Server.default)

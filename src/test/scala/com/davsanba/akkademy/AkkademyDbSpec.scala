package com.davsanba.akkademy

import akka.actor.ActorSystem
import akka.pattern.ask
import akka.testkit.TestActorRef
import akka.util.Timeout
import com.davsanba.akkademy.messages.{GetRequest, SetRequest}
import org.scalatest.{BeforeAndAfterEach, FunSpecLike, Matchers}

import scala.concurrent.Await
import scala.concurrent.duration._


class AkkademyDbSpec extends FunSpecLike with Matchers with
  BeforeAndAfterEach {

  implicit val timeout = Timeout(2 seconds)
  implicit val system = ActorSystem()

  describe("akkademyDb") {
    describe("given SetRequest") {
      it("should place key/value into map") {
        val actorRef = TestActorRef(new AkkademyDb)
        actorRef ! SetRequest("key", "value")
        val akkademyDb = actorRef.underlyingActor
        akkademyDb.map.get("key") should equal(Some("value"))
      }
      it("should set a value") {
        val actorRef = TestActorRef(new AkkademyDb)
        actorRef ? SetRequest("123", new Integer(123))
        val futureResult = actorRef ? GetRequest("123")
        val result = Await.result(futureResult, 5 seconds)
        result should equal(123)
      }
    }
  }
}
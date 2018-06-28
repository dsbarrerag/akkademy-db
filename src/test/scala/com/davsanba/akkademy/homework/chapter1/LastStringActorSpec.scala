package com.davsanba.akkademy.homework.chapter1

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import com.davsanba.akkademy.AkkademyDb
import com.davsanba.akkademy.messages.SetRequest
import org.scalatest.{BeforeAndAfterEach, FunSpecLike, Matchers}

class LastStringActorSpec extends FunSpecLike with Matchers with
  BeforeAndAfterEach {
  implicit val system = ActorSystem()
  describe("LastStringActor") {
    describe("given string") {
      it("should store the string") {
        val actorRef = TestActorRef(new LastStringActor)
        actorRef ! "value"
        val lastStringActor = actorRef.underlyingActor
        lastStringActor.lastString should equal(Some("value"))
      }
    }
    describe("given 2 string") {
      it("should store the last given string") {
        val actorRef = TestActorRef(new LastStringActor)
        actorRef ! "don't save this"
        actorRef ! "save this"
        val lastStringActor = actorRef.underlyingActor
        lastStringActor.lastString should equal(Some("save this"))
      }
    }
  }
}
package pivotal

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class PerformanceSimulation extends Simulation {

  val httpProtocol = http
    .baseURL("http://localhost:8081")
    .acceptHeader("text/plain")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Gatling")

  val scn = scenario("PerformanceSimulation")
    .repeat(1) {
      exec(http("GET /employee/getAllemployees").get("/employee/getAllemployees"))
    }

  setUp(
    scn.inject(atOnceUsers(2))
  ).protocols(httpProtocol)
}
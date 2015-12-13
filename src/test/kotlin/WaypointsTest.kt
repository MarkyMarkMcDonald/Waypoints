import org.junit.Test
import kotlin.test.assertEquals

class WaypointsTest {

    @Test
    fun testGivesWalkingDirections() {
        val waypoints = Waypoints(routes = listOf());

        val directions = waypoints.directions(50.0, 90.0, 80.0, 100.0);

        assertDirectionsAre(directions, "WALKING", 50.0, 90.0, 80.0, 100.0)
    }

    @Test
    fun testGivesTrainDirections() {
        val trainRoute = listOf(Point(51.0, 91.0), Point(79.0, 99.0))
        val waypoints = Waypoints(routes = listOf(Route("1 LINE", trainRoute)))

        val directions = waypoints.directions(50.0, 90.0, 80.0, 100.0);

        assertDirectionsAre(directions, "1 LINE", 51.0, 91.0, 79.0, 99.0)
    }

    @Test
    fun testGivesWalkingDirections_WhenTrainIsOutOfTheWay() {
        val trainRoute = listOf(Point(10.0, 10.0), Point(20.0, 20.0))
        val waypoints = Waypoints(routes = listOf(Route("1 LINE", trainRoute)))

        val directions = waypoints.directions(50.0, 90.0, 80.0, 100.0);

        assertDirectionsAre(directions, "WALKING", 50.0, 90.0, 80.0, 100.0)
    }

    @Test
    fun testGivesBusDirections() {
        val busRoute = listOf(Point(51.0, 91.0), Point(79.0, 99.0))
        val waypoints = Waypoints(routes = listOf(Route("ROUTE B54", busRoute)))

        val directions = waypoints.directions(50.0, 90.0, 80.0, 100.0);

        assertDirectionsAre(directions, "ROUTE B54", 51.0, 91.0, 79.0, 99.0)
    }

    private fun assertDirectionsAre(directions: Directions, type: String, startLatitude: Double, startLongitude: Double, endLatitude: Double, endLongitude: Double) {
        assertEquals(type, directions.type);
        assertEquals(directions.start.latitude, startLatitude)
        assertEquals(directions.start.longitude, startLongitude)
        assertEquals(directions.end.latitude, endLatitude)
        assertEquals(directions.end.longitude, endLongitude)
    }
}
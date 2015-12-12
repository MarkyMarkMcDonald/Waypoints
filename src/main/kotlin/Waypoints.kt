class Waypoints(val type: String = "", val points: List<Point> = listOf()) {

    val route: Route
    init {
        route = Route(type, points)
    }

    fun directions(startingLatitude: Double, startingLongitude: Double, endingLatitude: Double, endingLongitude: Double): Directions {
        val start = Point(startingLatitude, startingLongitude)
        val destination = Point(endingLatitude, endingLongitude)

        val defaultDirections = Directions(Route("WALKING", listOf(start, destination)))

        if (route.isNotEmpty() && publicTransitIsFaster(destination, start)) {
            return Directions(route)
        } else {
            return defaultDirections
        }
    }

    private fun publicTransitIsFaster(destination: Point, start: Point) = points.last() distanceTo destination < start distanceTo destination
}

class Route(val type: String, val points: List<Point>) {
    fun isNotEmpty(): Boolean {
        return points.isNotEmpty()
    }
}

data class Point(val latitude: Double, val longitude: Double) {
    @Deprecated("Will change to be earth based calculation in future")
    infix fun distanceTo(end: Point): Double {
        val latitudeDifference = this.latitude - end.latitude
        val longitudeDifference = this.longitude - end.longitude
        return Math.sqrt(Math.pow(latitudeDifference, 2.0) + Math.pow(longitudeDifference, 2.0))
    }
}

class Directions(val route:Route) {
    val end: Point
    val start: Point
    val type: String

    init {
        start = route.points.first()
        end = route.points.last()
        type = route.type
    }

}

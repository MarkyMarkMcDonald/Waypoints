class Waypoints(val route: Route?) {

    fun directions(startingLatitude: Double, startingLongitude: Double, endingLatitude: Double, endingLongitude: Double): Directions {
        val start = Point(startingLatitude, startingLongitude)
        val destination = Point(endingLatitude, endingLongitude)

        if (route != null && route.isNotEmpty() && publicTransitIsFaster(destination, start)) {
            return Directions(route)
        } else {
            val defaultDirections = Directions(Route("WALKING", listOf(start, destination)))
            return defaultDirections
        }
    }

    private fun publicTransitIsFaster(destination: Point, start: Point) = route != null && route.last() distanceTo destination < start distanceTo destination
}

class Route(val type: String, val points: List<Point>) {
    fun isNotEmpty(): Boolean {
        return points.isNotEmpty()
    }

    fun last(): Point {
        return points.last()
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

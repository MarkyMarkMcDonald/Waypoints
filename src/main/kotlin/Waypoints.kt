class Waypoints(val type: String = "", val points: List<Point> = listOf()) {

    fun directions(startingLatitude: Double, startingLongitude: Double, endingLatitude: Double, endingLongitude: Double): Directions {

        if (points.isNotEmpty()) {
            return Directions(type, points[0], points[1])
        }

        return Directions("WALKING",
                Point(startingLatitude, startingLongitude),
                Point(endingLatitude, endingLongitude)
        )
    }


}

data class Point(val latitude: Double, val longitude: Double) {}

data class Directions(val type: String, val start: Point, val end: Point) {}

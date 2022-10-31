package datn.cnpm.rcsystem.data.entitiy


data class PointResponse(val pointUsed: Int, var remainPoint: Int, val totalPoint: Int) {
    fun usePoint(pointUsed: Int) {
        remainPoint-=pointUsed
    }
}
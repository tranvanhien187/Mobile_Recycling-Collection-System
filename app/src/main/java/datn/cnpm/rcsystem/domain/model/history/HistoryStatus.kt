package datn.cnpm.rcsystem.domain.model.history

enum class HistoryStatus(val value: String) {
    CREATE("Created"), RECEIVE("On Driving"), COMPLETE("Complete"), CANCEL("Cancel")
}
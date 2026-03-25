package com.riverlevels.scotland.data.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\bH\'J\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\tH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0018\u0010\f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\r\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u0012\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0014H\u00a7@\u00a2\u0006\u0002\u0010\u0015J\u001e\u0010\u0016\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0014H\u00a7@\u00a2\u0006\u0002\u0010\u0015J(\u0010\u0017\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u0014H\u00a7@\u00a2\u0006\u0002\u0010\u001b\u00a8\u0006\u001c"}, d2 = {"Lcom/riverlevels/scotland/data/db/StationDao;", "", "delete", "", "station", "Lcom/riverlevels/scotland/data/db/MonitoredStation;", "(Lcom/riverlevels/scotland/data/db/MonitoredStation;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllStations", "Landroidx/lifecycle/LiveData;", "", "getEnabledStations", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStation", "stationNo", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "update", "updateAlertNotified", "time", "", "(Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateFloodNotified", "updateLevel", "level", "", "checkedAt", "(Ljava/lang/String;Ljava/lang/Double;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface StationDao {
    
    @androidx.room.Query(value = "SELECT * FROM monitored_stations ORDER BY stationName ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.riverlevels.scotland.data.db.MonitoredStation>> getAllStations();
    
    @androidx.room.Query(value = "SELECT * FROM monitored_stations WHERE isEnabled = 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getEnabledStations(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.riverlevels.scotland.data.db.MonitoredStation>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM monitored_stations WHERE stationNo = :stationNo")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getStation(@org.jetbrains.annotations.NotNull()
    java.lang.String stationNo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.riverlevels.scotland.data.db.MonitoredStation> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.riverlevels.scotland.data.db.MonitoredStation station, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.riverlevels.scotland.data.db.MonitoredStation station, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.riverlevels.scotland.data.db.MonitoredStation station, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE monitored_stations SET currentLevel = :level, lastChecked = :checkedAt WHERE stationNo = :stationNo")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateLevel(@org.jetbrains.annotations.NotNull()
    java.lang.String stationNo, @org.jetbrains.annotations.Nullable()
    java.lang.Double level, long checkedAt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE monitored_stations SET lastAlertNotified = :time WHERE stationNo = :stationNo")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateAlertNotified(@org.jetbrains.annotations.NotNull()
    java.lang.String stationNo, long time, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE monitored_stations SET lastFloodNotified = :time WHERE stationNo = :stationNo")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateFloodNotified(@org.jetbrains.annotations.NotNull()
    java.lang.String stationNo, long time, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}
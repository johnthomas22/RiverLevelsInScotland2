package com.riverlevels.scotland.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010\u0014J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0086@\u00a2\u0006\u0002\u0010\u0016J\u0018\u0010\u0017\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010\u0014J\u001c\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00072\u0006\u0010\u001a\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010\u0014J\u001e\u0010\u001b\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u001dH\u0086@\u00a2\u0006\u0002\u0010\u001eJ\u001e\u0010\u001f\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u001dH\u0086@\u00a2\u0006\u0002\u0010\u001eJ(\u0010 \u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010!\u001a\u0004\u0018\u00010\u00112\u0006\u0010\"\u001a\u00020\u001dH\u0086@\u00a2\u0006\u0002\u0010#J\u0016\u0010$\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006%"}, d2 = {"Lcom/riverlevels/scotland/data/repository/StationRepository;", "", "db", "Lcom/riverlevels/scotland/data/db/AppDatabase;", "(Lcom/riverlevels/scotland/data/db/AppDatabase;)V", "monitoredStations", "Landroidx/lifecycle/LiveData;", "", "Lcom/riverlevels/scotland/data/db/MonitoredStation;", "getMonitoredStations", "()Landroidx/lifecycle/LiveData;", "addStation", "", "station", "(Lcom/riverlevels/scotland/data/db/MonitoredStation;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteStation", "fetchCurrentLevel", "", "stationNo", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getEnabledStations", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStation", "searchSepaStations", "Lcom/riverlevels/scotland/data/model/StationInfo;", "query", "updateAlertNotified", "time", "", "(Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateFloodNotified", "updateLevel", "level", "checkedAt", "(Ljava/lang/String;Ljava/lang/Double;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateStation", "app_debug"})
public final class StationRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.riverlevels.scotland.data.db.AppDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.riverlevels.scotland.data.db.MonitoredStation>> monitoredStations = null;
    
    public StationRepository(@org.jetbrains.annotations.NotNull()
    com.riverlevels.scotland.data.db.AppDatabase db) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.riverlevels.scotland.data.db.MonitoredStation>> getMonitoredStations() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object searchSepaStations(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.riverlevels.scotland.data.model.StationInfo>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchCurrentLevel(@org.jetbrains.annotations.NotNull()
    java.lang.String stationNo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addStation(@org.jetbrains.annotations.NotNull()
    com.riverlevels.scotland.data.db.MonitoredStation station, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateStation(@org.jetbrains.annotations.NotNull()
    com.riverlevels.scotland.data.db.MonitoredStation station, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteStation(@org.jetbrains.annotations.NotNull()
    com.riverlevels.scotland.data.db.MonitoredStation station, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getStation(@org.jetbrains.annotations.NotNull()
    java.lang.String stationNo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.riverlevels.scotland.data.db.MonitoredStation> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getEnabledStations(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.riverlevels.scotland.data.db.MonitoredStation>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateLevel(@org.jetbrains.annotations.NotNull()
    java.lang.String stationNo, @org.jetbrains.annotations.Nullable()
    java.lang.Double level, long checkedAt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateAlertNotified(@org.jetbrains.annotations.NotNull()
    java.lang.String stationNo, long time, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateFloodNotified(@org.jetbrains.annotations.NotNull()
    java.lang.String stationNo, long time, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}
package com.riverlevels.scotland.data.api;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\n\bf\u0018\u00002\u00020\u0001Jf\u0010\u0002\u001a\u0014\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00030\u0003j\u0002`\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00042\b\b\u0003\u0010\u0007\u001a\u00020\u00042\b\b\u0003\u0010\b\u001a\u00020\t2\b\b\u0003\u0010\n\u001a\u00020\u00042\b\b\u0003\u0010\u000b\u001a\u00020\u00042\b\b\u0003\u0010\f\u001a\u00020\u00042\b\b\u0003\u0010\r\u001a\u00020\u0004H\u00a7@\u00a2\u0006\u0002\u0010\u000eJp\u0010\u000f\u001a\u0014\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00030\u0003j\u0002`\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00042\b\b\u0003\u0010\u0007\u001a\u00020\u00042\b\b\u0003\u0010\b\u001a\u00020\t2\b\b\u0003\u0010\n\u001a\u00020\u00042\b\b\u0001\u0010\u0010\u001a\u00020\u00042\b\b\u0003\u0010\u0011\u001a\u00020\u00042\b\b\u0003\u0010\f\u001a\u00020\u00042\b\b\u0003\u0010\r\u001a\u00020\u0004H\u00a7@\u00a2\u0006\u0002\u0010\u0012\u00a8\u0006\u0013"}, d2 = {"Lcom/riverlevels/scotland/data/api/SepaApiService;", "", "getStationList", "", "", "Lcom/riverlevels/scotland/data/api/SepaTable;", "service", "type", "datasource", "", "request", "stationparameterName", "returnfields", "format", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTimeseriesValues", "tsPath", "period", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface SepaApiService {
    
    @retrofit2.http.GET(value = ".")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getStationList(@retrofit2.http.Query(value = "service")
    @org.jetbrains.annotations.NotNull()
    java.lang.String service, @retrofit2.http.Query(value = "type")
    @org.jetbrains.annotations.NotNull()
    java.lang.String type, @retrofit2.http.Query(value = "datasource")
    int datasource, @retrofit2.http.Query(value = "request")
    @org.jetbrains.annotations.NotNull()
    java.lang.String request, @retrofit2.http.Query(value = "stationparameter_name")
    @org.jetbrains.annotations.NotNull()
    java.lang.String stationparameterName, @retrofit2.http.Query(value = "returnfields")
    @org.jetbrains.annotations.NotNull()
    java.lang.String returnfields, @retrofit2.http.Query(value = "format")
    @org.jetbrains.annotations.NotNull()
    java.lang.String format, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<? extends java.util.List<java.lang.String>>> $completion);
    
    @retrofit2.http.GET(value = ".")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTimeseriesValues(@retrofit2.http.Query(value = "service")
    @org.jetbrains.annotations.NotNull()
    java.lang.String service, @retrofit2.http.Query(value = "type")
    @org.jetbrains.annotations.NotNull()
    java.lang.String type, @retrofit2.http.Query(value = "datasource")
    int datasource, @retrofit2.http.Query(value = "request")
    @org.jetbrains.annotations.NotNull()
    java.lang.String request, @retrofit2.http.Query(value = "ts_path")
    @org.jetbrains.annotations.NotNull()
    java.lang.String tsPath, @retrofit2.http.Query(value = "period")
    @org.jetbrains.annotations.NotNull()
    java.lang.String period, @retrofit2.http.Query(value = "returnfields")
    @org.jetbrains.annotations.NotNull()
    java.lang.String returnfields, @retrofit2.http.Query(value = "format")
    @org.jetbrains.annotations.NotNull()
    java.lang.String format, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<? extends java.util.List<java.lang.String>>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}
package com.riverlevels.scotland.email;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J?\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\rJ\u001e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/riverlevels/scotland/email/EmailHelper;", "", "()V", "TAG", "", "buildAlertBody", "stationName", "riverName", "level", "", "alertLevel", "floodLevel", "type", "(Ljava/lang/String;Ljava/lang/String;DLjava/lang/Double;Ljava/lang/Double;Ljava/lang/String;)Ljava/lang/String;", "sendAlert", "", "prefs", "Lcom/riverlevels/scotland/util/AppPreferences;", "subject", "body", "app_debug"})
public final class EmailHelper {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "EmailHelper";
    @org.jetbrains.annotations.NotNull()
    public static final com.riverlevels.scotland.email.EmailHelper INSTANCE = null;
    
    private EmailHelper() {
        super();
    }
    
    public final void sendAlert(@org.jetbrains.annotations.NotNull()
    com.riverlevels.scotland.util.AppPreferences prefs, @org.jetbrains.annotations.NotNull()
    java.lang.String subject, @org.jetbrains.annotations.NotNull()
    java.lang.String body) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String buildAlertBody(@org.jetbrains.annotations.NotNull()
    java.lang.String stationName, @org.jetbrains.annotations.NotNull()
    java.lang.String riverName, double level, @org.jetbrains.annotations.Nullable()
    java.lang.Double alertLevel, @org.jetbrains.annotations.Nullable()
    java.lang.Double floodLevel, @org.jetbrains.annotations.NotNull()
    java.lang.String type) {
        return null;
    }
}
package com.riverlevels.scotland.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\n\u0018\u0000 .2\u00020\u0001:\u0001.B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R$\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u000e8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R$\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u00148F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R$\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u001d\u0010\t\"\u0004\b\u001e\u0010\u000bR$\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b \u0010\t\"\u0004\b!\u0010\u000bR$\u0010\"\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u00148F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b#\u0010\u0017\"\u0004\b$\u0010\u0019R$\u0010&\u001a\u00020%2\u0006\u0010\u0005\u001a\u00020%8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\'\u0010(\"\u0004\b)\u0010*R$\u0010+\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b,\u0010\t\"\u0004\b-\u0010\u000b\u00a8\u0006/"}, d2 = {"Lcom/riverlevels/scotland/util/AppPreferences;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "v", "", "alertEmailTo", "getAlertEmailTo", "()Ljava/lang/String;", "setAlertEmailTo", "(Ljava/lang/String;)V", "masterKey", "Landroidx/security/crypto/MasterKey;", "", "notificationCooldownMillis", "getNotificationCooldownMillis", "()J", "setNotificationCooldownMillis", "(J)V", "", "pollingIntervalMinutes", "getPollingIntervalMinutes", "()I", "setPollingIntervalMinutes", "(I)V", "prefs", "Landroid/content/SharedPreferences;", "smtpHost", "getSmtpHost", "setSmtpHost", "smtpPassword", "getSmtpPassword", "setSmtpPassword", "smtpPort", "getSmtpPort", "setSmtpPort", "", "smtpUseSsl", "getSmtpUseSsl", "()Z", "setSmtpUseSsl", "(Z)V", "smtpUsername", "getSmtpUsername", "setSmtpUsername", "Companion", "app_debug"})
public final class AppPreferences {
    @org.jetbrains.annotations.NotNull()
    private final androidx.security.crypto.MasterKey masterKey = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences prefs = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_SMTP_HOST = "smtp_host";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_SMTP_PORT = "smtp_port";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_SMTP_USER = "smtp_user";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_SMTP_PASS = "smtp_pass";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_EMAIL_TO = "email_to";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_POLL_INTERVAL = "poll_interval";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_NOTIF_COOLDOWN = "notif_cooldown";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_SMTP_SSL = "smtp_ssl";
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.riverlevels.scotland.util.AppPreferences INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.riverlevels.scotland.util.AppPreferences.Companion Companion = null;
    
    public AppPreferences(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSmtpHost() {
        return null;
    }
    
    public final void setSmtpHost(@org.jetbrains.annotations.NotNull()
    java.lang.String v) {
    }
    
    public final int getSmtpPort() {
        return 0;
    }
    
    public final void setSmtpPort(int v) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSmtpUsername() {
        return null;
    }
    
    public final void setSmtpUsername(@org.jetbrains.annotations.NotNull()
    java.lang.String v) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSmtpPassword() {
        return null;
    }
    
    public final void setSmtpPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String v) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAlertEmailTo() {
        return null;
    }
    
    public final void setAlertEmailTo(@org.jetbrains.annotations.NotNull()
    java.lang.String v) {
    }
    
    public final int getPollingIntervalMinutes() {
        return 0;
    }
    
    public final void setPollingIntervalMinutes(int v) {
    }
    
    public final long getNotificationCooldownMillis() {
        return 0L;
    }
    
    public final void setNotificationCooldownMillis(long v) {
    }
    
    public final boolean getSmtpUseSsl() {
        return false;
    }
    
    public final void setSmtpUseSsl(boolean v) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0010R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/riverlevels/scotland/util/AppPreferences$Companion;", "", "()V", "INSTANCE", "Lcom/riverlevels/scotland/util/AppPreferences;", "KEY_EMAIL_TO", "", "KEY_NOTIF_COOLDOWN", "KEY_POLL_INTERVAL", "KEY_SMTP_HOST", "KEY_SMTP_PASS", "KEY_SMTP_PORT", "KEY_SMTP_SSL", "KEY_SMTP_USER", "getInstance", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.riverlevels.scotland.util.AppPreferences getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}
package com.riverlevels.scotland.data.db;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class StationDao_Impl implements StationDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MonitoredStation> __insertionAdapterOfMonitoredStation;

  private final EntityDeletionOrUpdateAdapter<MonitoredStation> __deletionAdapterOfMonitoredStation;

  private final EntityDeletionOrUpdateAdapter<MonitoredStation> __updateAdapterOfMonitoredStation;

  private final SharedSQLiteStatement __preparedStmtOfUpdateLevel;

  private final SharedSQLiteStatement __preparedStmtOfUpdateAlertNotified;

  private final SharedSQLiteStatement __preparedStmtOfUpdateFloodNotified;

  public StationDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMonitoredStation = new EntityInsertionAdapter<MonitoredStation>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `monitored_stations` (`stationNo`,`stationName`,`riverName`,`alertLevel`,`floodLevel`,`currentLevel`,`lastChecked`,`lastAlertNotified`,`lastFloodNotified`,`isEnabled`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MonitoredStation entity) {
        if (entity.getStationNo() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getStationNo());
        }
        if (entity.getStationName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getStationName());
        }
        if (entity.getRiverName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getRiverName());
        }
        if (entity.getAlertLevel() == null) {
          statement.bindNull(4);
        } else {
          statement.bindDouble(4, entity.getAlertLevel());
        }
        if (entity.getFloodLevel() == null) {
          statement.bindNull(5);
        } else {
          statement.bindDouble(5, entity.getFloodLevel());
        }
        if (entity.getCurrentLevel() == null) {
          statement.bindNull(6);
        } else {
          statement.bindDouble(6, entity.getCurrentLevel());
        }
        statement.bindLong(7, entity.getLastChecked());
        statement.bindLong(8, entity.getLastAlertNotified());
        statement.bindLong(9, entity.getLastFloodNotified());
        final int _tmp = entity.isEnabled() ? 1 : 0;
        statement.bindLong(10, _tmp);
      }
    };
    this.__deletionAdapterOfMonitoredStation = new EntityDeletionOrUpdateAdapter<MonitoredStation>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `monitored_stations` WHERE `stationNo` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MonitoredStation entity) {
        if (entity.getStationNo() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getStationNo());
        }
      }
    };
    this.__updateAdapterOfMonitoredStation = new EntityDeletionOrUpdateAdapter<MonitoredStation>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `monitored_stations` SET `stationNo` = ?,`stationName` = ?,`riverName` = ?,`alertLevel` = ?,`floodLevel` = ?,`currentLevel` = ?,`lastChecked` = ?,`lastAlertNotified` = ?,`lastFloodNotified` = ?,`isEnabled` = ? WHERE `stationNo` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MonitoredStation entity) {
        if (entity.getStationNo() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getStationNo());
        }
        if (entity.getStationName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getStationName());
        }
        if (entity.getRiverName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getRiverName());
        }
        if (entity.getAlertLevel() == null) {
          statement.bindNull(4);
        } else {
          statement.bindDouble(4, entity.getAlertLevel());
        }
        if (entity.getFloodLevel() == null) {
          statement.bindNull(5);
        } else {
          statement.bindDouble(5, entity.getFloodLevel());
        }
        if (entity.getCurrentLevel() == null) {
          statement.bindNull(6);
        } else {
          statement.bindDouble(6, entity.getCurrentLevel());
        }
        statement.bindLong(7, entity.getLastChecked());
        statement.bindLong(8, entity.getLastAlertNotified());
        statement.bindLong(9, entity.getLastFloodNotified());
        final int _tmp = entity.isEnabled() ? 1 : 0;
        statement.bindLong(10, _tmp);
        if (entity.getStationNo() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getStationNo());
        }
      }
    };
    this.__preparedStmtOfUpdateLevel = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE monitored_stations SET currentLevel = ?, lastChecked = ? WHERE stationNo = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateAlertNotified = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE monitored_stations SET lastAlertNotified = ? WHERE stationNo = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateFloodNotified = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE monitored_stations SET lastFloodNotified = ? WHERE stationNo = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final MonitoredStation station,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMonitoredStation.insert(station);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final MonitoredStation station,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfMonitoredStation.handle(station);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final MonitoredStation station,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfMonitoredStation.handle(station);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateLevel(final String stationNo, final Double level, final long checkedAt,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateLevel.acquire();
        int _argIndex = 1;
        if (level == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindDouble(_argIndex, level);
        }
        _argIndex = 2;
        _stmt.bindLong(_argIndex, checkedAt);
        _argIndex = 3;
        if (stationNo == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, stationNo);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdateLevel.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object updateAlertNotified(final String stationNo, final long time,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateAlertNotified.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, time);
        _argIndex = 2;
        if (stationNo == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, stationNo);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdateAlertNotified.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object updateFloodNotified(final String stationNo, final long time,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateFloodNotified.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, time);
        _argIndex = 2;
        if (stationNo == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, stationNo);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdateFloodNotified.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<MonitoredStation>> getAllStations() {
    final String _sql = "SELECT * FROM monitored_stations ORDER BY stationName ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"monitored_stations"}, false, new Callable<List<MonitoredStation>>() {
      @Override
      @Nullable
      public List<MonitoredStation> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfStationNo = CursorUtil.getColumnIndexOrThrow(_cursor, "stationNo");
          final int _cursorIndexOfStationName = CursorUtil.getColumnIndexOrThrow(_cursor, "stationName");
          final int _cursorIndexOfRiverName = CursorUtil.getColumnIndexOrThrow(_cursor, "riverName");
          final int _cursorIndexOfAlertLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "alertLevel");
          final int _cursorIndexOfFloodLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "floodLevel");
          final int _cursorIndexOfCurrentLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "currentLevel");
          final int _cursorIndexOfLastChecked = CursorUtil.getColumnIndexOrThrow(_cursor, "lastChecked");
          final int _cursorIndexOfLastAlertNotified = CursorUtil.getColumnIndexOrThrow(_cursor, "lastAlertNotified");
          final int _cursorIndexOfLastFloodNotified = CursorUtil.getColumnIndexOrThrow(_cursor, "lastFloodNotified");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final List<MonitoredStation> _result = new ArrayList<MonitoredStation>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MonitoredStation _item;
            final String _tmpStationNo;
            if (_cursor.isNull(_cursorIndexOfStationNo)) {
              _tmpStationNo = null;
            } else {
              _tmpStationNo = _cursor.getString(_cursorIndexOfStationNo);
            }
            final String _tmpStationName;
            if (_cursor.isNull(_cursorIndexOfStationName)) {
              _tmpStationName = null;
            } else {
              _tmpStationName = _cursor.getString(_cursorIndexOfStationName);
            }
            final String _tmpRiverName;
            if (_cursor.isNull(_cursorIndexOfRiverName)) {
              _tmpRiverName = null;
            } else {
              _tmpRiverName = _cursor.getString(_cursorIndexOfRiverName);
            }
            final Double _tmpAlertLevel;
            if (_cursor.isNull(_cursorIndexOfAlertLevel)) {
              _tmpAlertLevel = null;
            } else {
              _tmpAlertLevel = _cursor.getDouble(_cursorIndexOfAlertLevel);
            }
            final Double _tmpFloodLevel;
            if (_cursor.isNull(_cursorIndexOfFloodLevel)) {
              _tmpFloodLevel = null;
            } else {
              _tmpFloodLevel = _cursor.getDouble(_cursorIndexOfFloodLevel);
            }
            final Double _tmpCurrentLevel;
            if (_cursor.isNull(_cursorIndexOfCurrentLevel)) {
              _tmpCurrentLevel = null;
            } else {
              _tmpCurrentLevel = _cursor.getDouble(_cursorIndexOfCurrentLevel);
            }
            final long _tmpLastChecked;
            _tmpLastChecked = _cursor.getLong(_cursorIndexOfLastChecked);
            final long _tmpLastAlertNotified;
            _tmpLastAlertNotified = _cursor.getLong(_cursorIndexOfLastAlertNotified);
            final long _tmpLastFloodNotified;
            _tmpLastFloodNotified = _cursor.getLong(_cursorIndexOfLastFloodNotified);
            final boolean _tmpIsEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp != 0;
            _item = new MonitoredStation(_tmpStationNo,_tmpStationName,_tmpRiverName,_tmpAlertLevel,_tmpFloodLevel,_tmpCurrentLevel,_tmpLastChecked,_tmpLastAlertNotified,_tmpLastFloodNotified,_tmpIsEnabled);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getEnabledStations(final Continuation<? super List<MonitoredStation>> $completion) {
    final String _sql = "SELECT * FROM monitored_stations WHERE isEnabled = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<MonitoredStation>>() {
      @Override
      @NonNull
      public List<MonitoredStation> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfStationNo = CursorUtil.getColumnIndexOrThrow(_cursor, "stationNo");
          final int _cursorIndexOfStationName = CursorUtil.getColumnIndexOrThrow(_cursor, "stationName");
          final int _cursorIndexOfRiverName = CursorUtil.getColumnIndexOrThrow(_cursor, "riverName");
          final int _cursorIndexOfAlertLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "alertLevel");
          final int _cursorIndexOfFloodLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "floodLevel");
          final int _cursorIndexOfCurrentLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "currentLevel");
          final int _cursorIndexOfLastChecked = CursorUtil.getColumnIndexOrThrow(_cursor, "lastChecked");
          final int _cursorIndexOfLastAlertNotified = CursorUtil.getColumnIndexOrThrow(_cursor, "lastAlertNotified");
          final int _cursorIndexOfLastFloodNotified = CursorUtil.getColumnIndexOrThrow(_cursor, "lastFloodNotified");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final List<MonitoredStation> _result = new ArrayList<MonitoredStation>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MonitoredStation _item;
            final String _tmpStationNo;
            if (_cursor.isNull(_cursorIndexOfStationNo)) {
              _tmpStationNo = null;
            } else {
              _tmpStationNo = _cursor.getString(_cursorIndexOfStationNo);
            }
            final String _tmpStationName;
            if (_cursor.isNull(_cursorIndexOfStationName)) {
              _tmpStationName = null;
            } else {
              _tmpStationName = _cursor.getString(_cursorIndexOfStationName);
            }
            final String _tmpRiverName;
            if (_cursor.isNull(_cursorIndexOfRiverName)) {
              _tmpRiverName = null;
            } else {
              _tmpRiverName = _cursor.getString(_cursorIndexOfRiverName);
            }
            final Double _tmpAlertLevel;
            if (_cursor.isNull(_cursorIndexOfAlertLevel)) {
              _tmpAlertLevel = null;
            } else {
              _tmpAlertLevel = _cursor.getDouble(_cursorIndexOfAlertLevel);
            }
            final Double _tmpFloodLevel;
            if (_cursor.isNull(_cursorIndexOfFloodLevel)) {
              _tmpFloodLevel = null;
            } else {
              _tmpFloodLevel = _cursor.getDouble(_cursorIndexOfFloodLevel);
            }
            final Double _tmpCurrentLevel;
            if (_cursor.isNull(_cursorIndexOfCurrentLevel)) {
              _tmpCurrentLevel = null;
            } else {
              _tmpCurrentLevel = _cursor.getDouble(_cursorIndexOfCurrentLevel);
            }
            final long _tmpLastChecked;
            _tmpLastChecked = _cursor.getLong(_cursorIndexOfLastChecked);
            final long _tmpLastAlertNotified;
            _tmpLastAlertNotified = _cursor.getLong(_cursorIndexOfLastAlertNotified);
            final long _tmpLastFloodNotified;
            _tmpLastFloodNotified = _cursor.getLong(_cursorIndexOfLastFloodNotified);
            final boolean _tmpIsEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp != 0;
            _item = new MonitoredStation(_tmpStationNo,_tmpStationName,_tmpRiverName,_tmpAlertLevel,_tmpFloodLevel,_tmpCurrentLevel,_tmpLastChecked,_tmpLastAlertNotified,_tmpLastFloodNotified,_tmpIsEnabled);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getStation(final String stationNo,
      final Continuation<? super MonitoredStation> $completion) {
    final String _sql = "SELECT * FROM monitored_stations WHERE stationNo = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (stationNo == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, stationNo);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<MonitoredStation>() {
      @Override
      @Nullable
      public MonitoredStation call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfStationNo = CursorUtil.getColumnIndexOrThrow(_cursor, "stationNo");
          final int _cursorIndexOfStationName = CursorUtil.getColumnIndexOrThrow(_cursor, "stationName");
          final int _cursorIndexOfRiverName = CursorUtil.getColumnIndexOrThrow(_cursor, "riverName");
          final int _cursorIndexOfAlertLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "alertLevel");
          final int _cursorIndexOfFloodLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "floodLevel");
          final int _cursorIndexOfCurrentLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "currentLevel");
          final int _cursorIndexOfLastChecked = CursorUtil.getColumnIndexOrThrow(_cursor, "lastChecked");
          final int _cursorIndexOfLastAlertNotified = CursorUtil.getColumnIndexOrThrow(_cursor, "lastAlertNotified");
          final int _cursorIndexOfLastFloodNotified = CursorUtil.getColumnIndexOrThrow(_cursor, "lastFloodNotified");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final MonitoredStation _result;
          if (_cursor.moveToFirst()) {
            final String _tmpStationNo;
            if (_cursor.isNull(_cursorIndexOfStationNo)) {
              _tmpStationNo = null;
            } else {
              _tmpStationNo = _cursor.getString(_cursorIndexOfStationNo);
            }
            final String _tmpStationName;
            if (_cursor.isNull(_cursorIndexOfStationName)) {
              _tmpStationName = null;
            } else {
              _tmpStationName = _cursor.getString(_cursorIndexOfStationName);
            }
            final String _tmpRiverName;
            if (_cursor.isNull(_cursorIndexOfRiverName)) {
              _tmpRiverName = null;
            } else {
              _tmpRiverName = _cursor.getString(_cursorIndexOfRiverName);
            }
            final Double _tmpAlertLevel;
            if (_cursor.isNull(_cursorIndexOfAlertLevel)) {
              _tmpAlertLevel = null;
            } else {
              _tmpAlertLevel = _cursor.getDouble(_cursorIndexOfAlertLevel);
            }
            final Double _tmpFloodLevel;
            if (_cursor.isNull(_cursorIndexOfFloodLevel)) {
              _tmpFloodLevel = null;
            } else {
              _tmpFloodLevel = _cursor.getDouble(_cursorIndexOfFloodLevel);
            }
            final Double _tmpCurrentLevel;
            if (_cursor.isNull(_cursorIndexOfCurrentLevel)) {
              _tmpCurrentLevel = null;
            } else {
              _tmpCurrentLevel = _cursor.getDouble(_cursorIndexOfCurrentLevel);
            }
            final long _tmpLastChecked;
            _tmpLastChecked = _cursor.getLong(_cursorIndexOfLastChecked);
            final long _tmpLastAlertNotified;
            _tmpLastAlertNotified = _cursor.getLong(_cursorIndexOfLastAlertNotified);
            final long _tmpLastFloodNotified;
            _tmpLastFloodNotified = _cursor.getLong(_cursorIndexOfLastFloodNotified);
            final boolean _tmpIsEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp != 0;
            _result = new MonitoredStation(_tmpStationNo,_tmpStationName,_tmpRiverName,_tmpAlertLevel,_tmpFloodLevel,_tmpCurrentLevel,_tmpLastChecked,_tmpLastAlertNotified,_tmpLastFloodNotified,_tmpIsEnabled);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}

package od.app.db;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.EntityUpsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class CharacterDao_Impl implements CharacterDao {
  private final RoomDatabase __db;

  private final SharedSQLiteStatement __preparedStmtOfNuke;

  private final EntityUpsertionAdapter<CharacterEntity> __upsertionAdapterOfCharacterEntity;

  public CharacterDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__preparedStmtOfNuke = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM characters";
        return _query;
      }
    };
    this.__upsertionAdapterOfCharacterEntity = new EntityUpsertionAdapter<CharacterEntity>(new EntityInsertionAdapter<CharacterEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT INTO `characters` (`id`,`name`,`race`,`clazz`,`str`,`dex`,`con`,`intel`,`wis`,`cha`,`atk`,`hp`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CharacterEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getRace());
        statement.bindString(4, entity.getClazz());
        statement.bindLong(5, entity.getStr());
        statement.bindLong(6, entity.getDex());
        statement.bindLong(7, entity.getCon());
        statement.bindLong(8, entity.getIntel());
        statement.bindLong(9, entity.getWis());
        statement.bindLong(10, entity.getCha());
        statement.bindLong(11, entity.getAtk());
        statement.bindLong(12, entity.getHp());
      }
    }, new EntityDeletionOrUpdateAdapter<CharacterEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE `characters` SET `id` = ?,`name` = ?,`race` = ?,`clazz` = ?,`str` = ?,`dex` = ?,`con` = ?,`intel` = ?,`wis` = ?,`cha` = ?,`atk` = ?,`hp` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CharacterEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getRace());
        statement.bindString(4, entity.getClazz());
        statement.bindLong(5, entity.getStr());
        statement.bindLong(6, entity.getDex());
        statement.bindLong(7, entity.getCon());
        statement.bindLong(8, entity.getIntel());
        statement.bindLong(9, entity.getWis());
        statement.bindLong(10, entity.getCha());
        statement.bindLong(11, entity.getAtk());
        statement.bindLong(12, entity.getHp());
        statement.bindLong(13, entity.getId());
      }
    });
  }

  @Override
  public Object nuke(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfNuke.acquire();
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
          __preparedStmtOfNuke.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object upsert(final CharacterEntity c, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __upsertionAdapterOfCharacterEntity.upsertAndReturnId(c);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<CharacterEntity> first() {
    final String _sql = "SELECT * FROM characters ORDER BY id LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"characters"}, new Callable<CharacterEntity>() {
      @Override
      @Nullable
      public CharacterEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfRace = CursorUtil.getColumnIndexOrThrow(_cursor, "race");
          final int _cursorIndexOfClazz = CursorUtil.getColumnIndexOrThrow(_cursor, "clazz");
          final int _cursorIndexOfStr = CursorUtil.getColumnIndexOrThrow(_cursor, "str");
          final int _cursorIndexOfDex = CursorUtil.getColumnIndexOrThrow(_cursor, "dex");
          final int _cursorIndexOfCon = CursorUtil.getColumnIndexOrThrow(_cursor, "con");
          final int _cursorIndexOfIntel = CursorUtil.getColumnIndexOrThrow(_cursor, "intel");
          final int _cursorIndexOfWis = CursorUtil.getColumnIndexOrThrow(_cursor, "wis");
          final int _cursorIndexOfCha = CursorUtil.getColumnIndexOrThrow(_cursor, "cha");
          final int _cursorIndexOfAtk = CursorUtil.getColumnIndexOrThrow(_cursor, "atk");
          final int _cursorIndexOfHp = CursorUtil.getColumnIndexOrThrow(_cursor, "hp");
          final CharacterEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpRace;
            _tmpRace = _cursor.getString(_cursorIndexOfRace);
            final String _tmpClazz;
            _tmpClazz = _cursor.getString(_cursorIndexOfClazz);
            final int _tmpStr;
            _tmpStr = _cursor.getInt(_cursorIndexOfStr);
            final int _tmpDex;
            _tmpDex = _cursor.getInt(_cursorIndexOfDex);
            final int _tmpCon;
            _tmpCon = _cursor.getInt(_cursorIndexOfCon);
            final int _tmpIntel;
            _tmpIntel = _cursor.getInt(_cursorIndexOfIntel);
            final int _tmpWis;
            _tmpWis = _cursor.getInt(_cursorIndexOfWis);
            final int _tmpCha;
            _tmpCha = _cursor.getInt(_cursorIndexOfCha);
            final int _tmpAtk;
            _tmpAtk = _cursor.getInt(_cursorIndexOfAtk);
            final int _tmpHp;
            _tmpHp = _cursor.getInt(_cursorIndexOfHp);
            _result = new CharacterEntity(_tmpId,_tmpName,_tmpRace,_tmpClazz,_tmpStr,_tmpDex,_tmpCon,_tmpIntel,_tmpWis,_tmpCha,_tmpAtk,_tmpHp);
          } else {
            _result = null;
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
  public Object firstOnce(final Continuation<? super CharacterEntity> $completion) {
    final String _sql = "SELECT * FROM characters ORDER BY id LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<CharacterEntity>() {
      @Override
      @Nullable
      public CharacterEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfRace = CursorUtil.getColumnIndexOrThrow(_cursor, "race");
          final int _cursorIndexOfClazz = CursorUtil.getColumnIndexOrThrow(_cursor, "clazz");
          final int _cursorIndexOfStr = CursorUtil.getColumnIndexOrThrow(_cursor, "str");
          final int _cursorIndexOfDex = CursorUtil.getColumnIndexOrThrow(_cursor, "dex");
          final int _cursorIndexOfCon = CursorUtil.getColumnIndexOrThrow(_cursor, "con");
          final int _cursorIndexOfIntel = CursorUtil.getColumnIndexOrThrow(_cursor, "intel");
          final int _cursorIndexOfWis = CursorUtil.getColumnIndexOrThrow(_cursor, "wis");
          final int _cursorIndexOfCha = CursorUtil.getColumnIndexOrThrow(_cursor, "cha");
          final int _cursorIndexOfAtk = CursorUtil.getColumnIndexOrThrow(_cursor, "atk");
          final int _cursorIndexOfHp = CursorUtil.getColumnIndexOrThrow(_cursor, "hp");
          final CharacterEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpRace;
            _tmpRace = _cursor.getString(_cursorIndexOfRace);
            final String _tmpClazz;
            _tmpClazz = _cursor.getString(_cursorIndexOfClazz);
            final int _tmpStr;
            _tmpStr = _cursor.getInt(_cursorIndexOfStr);
            final int _tmpDex;
            _tmpDex = _cursor.getInt(_cursorIndexOfDex);
            final int _tmpCon;
            _tmpCon = _cursor.getInt(_cursorIndexOfCon);
            final int _tmpIntel;
            _tmpIntel = _cursor.getInt(_cursorIndexOfIntel);
            final int _tmpWis;
            _tmpWis = _cursor.getInt(_cursorIndexOfWis);
            final int _tmpCha;
            _tmpCha = _cursor.getInt(_cursorIndexOfCha);
            final int _tmpAtk;
            _tmpAtk = _cursor.getInt(_cursorIndexOfAtk);
            final int _tmpHp;
            _tmpHp = _cursor.getInt(_cursorIndexOfHp);
            _result = new CharacterEntity(_tmpId,_tmpName,_tmpRace,_tmpClazz,_tmpStr,_tmpDex,_tmpCon,_tmpIntel,_tmpWis,_tmpCha,_tmpAtk,_tmpHp);
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

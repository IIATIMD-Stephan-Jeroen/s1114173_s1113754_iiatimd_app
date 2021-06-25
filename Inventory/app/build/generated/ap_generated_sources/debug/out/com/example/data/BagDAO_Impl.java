package com.example.data;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class BagDAO_Impl implements BagDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Bag> __insertionAdapterOfBag;

  private final EntityDeletionOrUpdateAdapter<Bag> __deletionAdapterOfBag;

  public BagDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBag = new EntityInsertionAdapter<Bag>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Bag` (`name`,`description`,`id`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Bag value) {
        if (value.getName() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getDescription());
        }
        stmt.bindLong(3, value.getId());
      }
    };
    this.__deletionAdapterOfBag = new EntityDeletionOrUpdateAdapter<Bag>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Bag` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Bag value) {
        stmt.bindLong(1, value.getId());
      }
    };
  }

  @Override
  public void InsertBag(final Bag bag) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfBag.insert(bag);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Bag bag) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfBag.handle(bag);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Bag> getAll() {
    final String _sql = "SELECT * FROM bag";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final List<Bag> _result = new ArrayList<Bag>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Bag _item;
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item = new Bag(_tmpName,_tmpDescription,_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}

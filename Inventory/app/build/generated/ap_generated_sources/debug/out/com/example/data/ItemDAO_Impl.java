package com.example.data;

import android.database.Cursor;
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
public final class ItemDAO_Impl implements ItemDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Item> __insertionAdapterOfItem;

  public ItemDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfItem = new EntityInsertionAdapter<Item>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Item` (`id`,`name`,`cost`,`currency`,`type`,`weight`,`damage`,`damage_type`,`property_1`,`property_2`,`property_3`,`property_4`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Item value) {
        stmt.bindLong(1, value.id);
        if (value.name == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.name);
        }
        if (value.cost == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.cost);
        }
        if (value.currency == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.currency);
        }
        if (value.type == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.type);
        }
        if (value.weight == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.weight);
        }
        if (value.damage == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.damage);
        }
        if (value.damage_type == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.damage_type);
        }
        if (value.property_1 == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.property_1);
        }
        if (value.property_2 == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.property_2);
        }
        if (value.property_3 == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.property_3);
        }
        if (value.property_4 == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.property_4);
        }
      }
    };
  }

  @Override
  public void insertItem(final Item... items) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfItem.insert(items);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Item> getAllItems() {
    final String _sql = "SELECT * FROM item";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfCost = CursorUtil.getColumnIndexOrThrow(_cursor, "cost");
      final int _cursorIndexOfCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "currency");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
      final int _cursorIndexOfDamage = CursorUtil.getColumnIndexOrThrow(_cursor, "damage");
      final int _cursorIndexOfDamageType = CursorUtil.getColumnIndexOrThrow(_cursor, "damage_type");
      final int _cursorIndexOfProperty1 = CursorUtil.getColumnIndexOrThrow(_cursor, "property_1");
      final int _cursorIndexOfProperty2 = CursorUtil.getColumnIndexOrThrow(_cursor, "property_2");
      final int _cursorIndexOfProperty3 = CursorUtil.getColumnIndexOrThrow(_cursor, "property_3");
      final int _cursorIndexOfProperty4 = CursorUtil.getColumnIndexOrThrow(_cursor, "property_4");
      final List<Item> _result = new ArrayList<Item>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Item _item;
        _item = new Item();
        _item.id = _cursor.getInt(_cursorIndexOfId);
        if (_cursor.isNull(_cursorIndexOfName)) {
          _item.name = null;
        } else {
          _item.name = _cursor.getString(_cursorIndexOfName);
        }
        if (_cursor.isNull(_cursorIndexOfCost)) {
          _item.cost = null;
        } else {
          _item.cost = _cursor.getString(_cursorIndexOfCost);
        }
        if (_cursor.isNull(_cursorIndexOfCurrency)) {
          _item.currency = null;
        } else {
          _item.currency = _cursor.getString(_cursorIndexOfCurrency);
        }
        if (_cursor.isNull(_cursorIndexOfType)) {
          _item.type = null;
        } else {
          _item.type = _cursor.getString(_cursorIndexOfType);
        }
        if (_cursor.isNull(_cursorIndexOfWeight)) {
          _item.weight = null;
        } else {
          _item.weight = _cursor.getString(_cursorIndexOfWeight);
        }
        if (_cursor.isNull(_cursorIndexOfDamage)) {
          _item.damage = null;
        } else {
          _item.damage = _cursor.getString(_cursorIndexOfDamage);
        }
        if (_cursor.isNull(_cursorIndexOfDamageType)) {
          _item.damage_type = null;
        } else {
          _item.damage_type = _cursor.getString(_cursorIndexOfDamageType);
        }
        if (_cursor.isNull(_cursorIndexOfProperty1)) {
          _item.property_1 = null;
        } else {
          _item.property_1 = _cursor.getString(_cursorIndexOfProperty1);
        }
        if (_cursor.isNull(_cursorIndexOfProperty2)) {
          _item.property_2 = null;
        } else {
          _item.property_2 = _cursor.getString(_cursorIndexOfProperty2);
        }
        if (_cursor.isNull(_cursorIndexOfProperty3)) {
          _item.property_3 = null;
        } else {
          _item.property_3 = _cursor.getString(_cursorIndexOfProperty3);
        }
        if (_cursor.isNull(_cursorIndexOfProperty4)) {
          _item.property_4 = null;
        } else {
          _item.property_4 = _cursor.getString(_cursorIndexOfProperty4);
        }
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

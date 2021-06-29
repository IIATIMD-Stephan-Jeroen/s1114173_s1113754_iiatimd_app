package com.example.data;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ItemDatabase_Impl extends ItemDatabase {
  private volatile ItemDAO _itemDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Item` (`id` INTEGER NOT NULL, `name` TEXT, `cost` TEXT, `currency` TEXT, `type` TEXT, `weight` TEXT, `damage` TEXT, `damage_type` TEXT, `property_1` TEXT, `property_2` TEXT, `property_3` TEXT, `property_4` TEXT, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '3300a0e17ab36c3475aa2681192ffb2b')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Item`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsItem = new HashMap<String, TableInfo.Column>(12);
        _columnsItem.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItem.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItem.put("cost", new TableInfo.Column("cost", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItem.put("currency", new TableInfo.Column("currency", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItem.put("type", new TableInfo.Column("type", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItem.put("weight", new TableInfo.Column("weight", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItem.put("damage", new TableInfo.Column("damage", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItem.put("damage_type", new TableInfo.Column("damage_type", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItem.put("property_1", new TableInfo.Column("property_1", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItem.put("property_2", new TableInfo.Column("property_2", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItem.put("property_3", new TableInfo.Column("property_3", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItem.put("property_4", new TableInfo.Column("property_4", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysItem = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesItem = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoItem = new TableInfo("Item", _columnsItem, _foreignKeysItem, _indicesItem);
        final TableInfo _existingItem = TableInfo.read(_db, "Item");
        if (! _infoItem.equals(_existingItem)) {
          return new RoomOpenHelper.ValidationResult(false, "Item(com.example.data.Item).\n"
                  + " Expected:\n" + _infoItem + "\n"
                  + " Found:\n" + _existingItem);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "3300a0e17ab36c3475aa2681192ffb2b", "06bd9976d1b12eafeb512b8a958c4df8");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "Item");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Item`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(ItemDAO.class, ItemDAO_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public ItemDAO itemDao() {
    if (_itemDAO != null) {
      return _itemDAO;
    } else {
      synchronized(this) {
        if(_itemDAO == null) {
          _itemDAO = new ItemDAO_Impl(this);
        }
        return _itemDAO;
      }
    }
  }
}

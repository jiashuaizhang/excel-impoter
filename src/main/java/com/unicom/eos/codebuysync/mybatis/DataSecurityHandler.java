package com.unicom.eos.codebuysync.mybatis;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import com.unicom.eos.codebuysync.util.crypt.SimpleAesSensitiveCrypter;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

/**
 * 加解密handler
 *
 * @author unisk1123
 * @create 2018/10/9
 */
@SuppressWarnings("rawtypes")
public class DataSecurityHandler extends BaseTypeHandler {

    private static SimpleAesSensitiveCrypter crypter;

    {
        crypter = SimpleAesSensitiveCrypter.getInstance();
    }

    public static final LoadingCache<String, String> ENCRYPT_CACHE = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES).build(new CacheLoader<String, String>() {
        public String load(String parameterValue) {
            return crypter.encrypt(parameterValue);
        }
    });

    public static final LoadingCache<String, String> DECRYPT_CACHE = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES).build(new CacheLoader<String, String>() {
        public String load(String parameterValue) {
            return crypter.decrypt(parameterValue);
        }
    });


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {

        String parameterValue = (String) parameter;
        String resultValue = ENCRYPT_CACHE.getUnchecked(StringUtils.trimToEmpty(parameterValue));
        ps.setString(i, resultValue);
    }

    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return DECRYPT_CACHE.getUnchecked(StringUtils.trimToEmpty(rs.getString(columnName)));
    }

    @Override
    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return DECRYPT_CACHE.getUnchecked(StringUtils.trimToEmpty(rs.getString(columnIndex)));
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return DECRYPT_CACHE.getUnchecked(StringUtils.trimToEmpty(cs.getString(columnIndex)));
    }
}

package com.bupt.smarthome.dao.impl;

import com.bupt.smarthome.dao.UserDAO;
import com.bupt.smarthome.ds.Druid;
import com.bupt.smarthome.vo.*;
import com.bupt.smarthome.vo.highchart.Series;
import com.bupt.smarthome.vo.highchart.Title;
import com.bupt.smarthome.vo.highchart.YAxis;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDAOImpl implements UserDAO {
	private final JdbcTemplate TEMPLATE = new JdbcTemplate(Druid.getDataSource());

	@Override
	public UserFamily registerDAO(UserFamily user) {
		String sql1 = "select familyid from family where sign = 1 and faddress = ?";
		String sql2 = "select username from user where sign = 1 and username = ?";
		String sql3 = "replace into user values (?, ?, ?, ?, ?, 1)"; // replace data with same username and sign = 0

		List<UserFamily> ls = TEMPLATE.query(sql1, new BeanPropertyRowMapper<>(UserFamily.class), user.getfAddress());

		if (ls.isEmpty())// ignore the case that family sign = 0
			return user;
		else {
			user.setFamilyId(ls.get(0).getFamilyId());

			ls = TEMPLATE.query(sql2, new BeanPropertyRowMapper<>(UserFamily.class), user.getUsername());
			if (ls.isEmpty()) { // no duplicated username
				TEMPLATE.update(sql3, user.getUsername(), user.getuPassword(), user.getfName(), user.getlName(), user.getFamilyId());
				return user;
			}
			return null;
		}
	}

	@Override
	public boolean editPasswordDAO(UserFamily user) {
		String sql = "update user, family set upassword = ? where family_familyid = familyid and user.sign = 1 and username = ? and fname = ? and lname = ? and faddress = ?";
		int count = TEMPLATE.update(sql, user.getuPassword(), user.getUsername(), user.getfName(), user.getlName(), user.getfAddress());
		return count == 1;
	}

	@Override
	public boolean deregisterDAO(UserFamily user) {
		String sql = "update user set sign = 0 where sign = 1 and username = ? and upassword = ?";
		int count = TEMPLATE.update(sql, user.getUsername(), user.getuPassword());
		return count == 1;
	}

	@Override
	public UserFamily userFamilyDAO(UserFamily user) {
		String sql = "select * from user, family where family_familyid = familyid and user.sign = 1 and username = ? and upassword = ?";
		List<UserFamily> ls = TEMPLATE.query(sql, new BeanPropertyRowMapper<>(UserFamily.class), user.getUsername(), user.getuPassword());

		if (ls.isEmpty())
			return null;
		return ls.get(0);
	}

	@Override
	public List<FamilyHardware> familyHardwareDAO(UserFamily user, int hwTypeId) {
		String sql = "select * from family, belonging, hardware, manufacturer, hwtype where familyid = family_familyid and hardware_hardwareid = hardwareid and manufacturer_manufacturerid = manufacturerid and hwtype_hwtypeid = hwtypeid and belonging.sign = 1 and familyid = ? and hwtypeid = ?";
		List<FamilyHardware> ls = TEMPLATE.query(sql, new BeanPropertyRowMapper<>(FamilyHardware.class), user.getFamilyId(), hwTypeId);

		if (ls.isEmpty())
			return null;
		return ls;
	}

	@Override
	public YAxis yAxisDAO(FamilyHardware hardware, int dataTypeId) {
		String sql = "select * from datatype where sign = 1 and datatypeid = ?";
		List<YAxis> ls = TEMPLATE.query(sql, new RowMapper<YAxis>() {
			@Override
			public YAxis mapRow(ResultSet rs, int i) throws SQLException {
				return (new YAxis(new Title(rs.getString("dtTypeName")), rs.getDouble("minVal"), rs.getDouble("maxVal")));
			}
		}, dataTypeId);

		if (ls.isEmpty())
			return null;
		return ls.get(0);
	}

	@Override
	public Series seriesDAO (FamilyHardware hardware) {
		String sql = "select datatime, datavalue from data where sign = 1 and familyId = ? and hardwareId = ? order by datatime";
		List<Map<String, Object>> ml = TEMPLATE.queryForList(sql, hardware.getFamilyId(), hardware.getHardwareId());
		
		if (ml.isEmpty())
			return null;

		ArrayList<ArrayList<Double>> al = new ArrayList<>();
		ArrayList<Double> dl = null;

		for (Map<String, Object> m : ml) {
			dl = new ArrayList<>();
			dl.add((double) ((LocalDateTime) m.get("dataTime")).toInstant(ZoneOffset.UTC).toEpochMilli());
			dl.add((Double) m.get("dataValue"));
			al.add(dl);
		}

		return (new Series(hardware.getHwName(), al));
	}
}

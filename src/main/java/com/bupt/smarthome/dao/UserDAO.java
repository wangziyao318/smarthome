package com.bupt.smarthome.dao;

import com.bupt.smarthome.vo.*;
import com.bupt.smarthome.vo.highchart.Series;
import com.bupt.smarthome.vo.highchart.YAxis;

import java.util.List;

/**
 * This interface describes database operations concerning users
 * @author wzy
 * @version 1.0
 */
public interface UserDAO {
	/**
	 * Insert new userFamily into mysql where fAddress must correspond to mysql data
	 * Set sign = 1 if register family address the same as deleted family
	 * Set sign = 1 if register data is the same as closed account
	 * @param user
	 * @return
	 */
	public UserFamily registerDAO (UserFamily user);

	/**
	 * Edit (or reset) uPassword given username, fName, and lName
	 * @param user
	 * @return
	 */
	public boolean editPasswordDAO (UserFamily user);

	/**
	 * Delete userFamily account by setting sign to 0
	 * @param user
	 * @return
	 */
	public boolean deregisterDAO (UserFamily user);

	/**
	 * Query joint table of user and family
	 * Handles userFamily login by matching username and uPassword with mysql data
	 * @param user
	 * @return Return null if wrong username or upassword
	 */
	public UserFamily userFamilyDAO (UserFamily user);

	/**
	 * Query joint table of family, belonging, hardware, manufacturer, and hwtype given hardware type
	 * @param user
	 * @return Return null if the family doesn't have a hardware
	 */
	public List<FamilyHardware> familyHardwareDAO (UserFamily user, int hwTypeId);

	/**
	 * Query for data type, min, max
	 * @param hardware
	 * @param dataTypeId
	 * @return
	 */
	public YAxis yAxisDAO(FamilyHardware hardware, int dataTypeId);

	/**
	 * Query for datetime, data
	 * @param hardware
	 * @return
	 */
	public Series seriesDAO (FamilyHardware hardware);
}

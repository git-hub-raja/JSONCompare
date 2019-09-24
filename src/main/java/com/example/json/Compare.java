package com.example.json;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareResult;
import org.skyscreamer.jsonassert.comparator.JSONComparator;

public class Compare {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(" ========== START ==========\n");
		String expectedJSON = "";
		String actualJSON = "";
		try {
			JSONCompareResult result = JSONCompare.compareJSON(expectedJSON, actualJSON, new customJSONComparator());
			System.out.println("Failed : " + result.failed());
			System.out.println("Passed : " + result.passed());
			System.out.println("result : " + result);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		System.out.println(" ==========  END  ==========");
	}

}

class customJSONComparator implements JSONComparator {

	public JSONCompareResult compareJSON(JSONObject arg0, JSONObject arg1) throws JSONException {
		// TODO Auto-generated method stub
		return null;
	}

	public JSONCompareResult compareJSON(JSONArray arg0, JSONArray arg1) throws JSONException {
		// TODO Auto-generated method stub
		JSONCompareResult compareResult = null;
		JSONObject jObj = null, jObj2 = null;
		try {
			compareResult = new JSONCompareResult();
			if (arg0 != null && arg1 != null) {
				if (arg0.length() > 0 && arg1.length() > 0 && arg0.length() == arg1.length()) {
					for (int itr = 0; itr < arg0.length(); itr++) {
						jObj = arg0.getJSONObject(itr);
						jObj2 = arg1.getJSONObject(itr);
						if (jObj.length() == jObj2.length()) {
							Iterator iterator = jObj.keys();
							while (iterator.hasNext()) {
								String key = (String) iterator.next();
								String v1 = "", v2 = "";
								if(!(jObj.get(key) instanceof String)) {
									v1 = String.valueOf((jObj.get(key)));
								}else {
									v1 = (String) jObj.get(key);
								}
								if(!(jObj2.get(key) instanceof String)) {
									v2 = String.valueOf((jObj2.get(key)));
								}else {
									v2 = (String) jObj2.get(key);
								}
								if ("OpsStart".equalsIgnoreCase(key) || "OpsEnd".equalsIgnoreCase(key)
										|| "UpdatedDate".equalsIgnoreCase(key)) {
									if (!v1.substring(0, (v1).indexOf("T"))
											.equalsIgnoreCase(v2.substring(0,
													(v2).indexOf("T")))) {
										compareResult.fail("Data mismatching at index \"" + itr
												+ "\" with the key \"" + key + "\".",
												v1.substring(0, (v1).indexOf("T")),
												v2.substring(0, (v2).indexOf("T")));
									}
								} else if (!(v1).equalsIgnoreCase(v2)) {
									compareResult.fail("Data mismatching at index \"" + itr
											+ "\" with the key \"" + key + "\".", v1,
											v2);
								} else {

								}
							}
						} else {
							compareResult.fail("Data size mismatching at index \"" + itr + "\".", jObj.length(),
									jObj2.length());
						}
					}
				} else {
					if (arg0.length() != arg1.length())
						compareResult.fail("Data size mismatching.", arg0.length(), arg1.length());
				}
			} else {
				compareResult.fail("Invalid Data", arg0, arg1);
			}
		} catch (Exception exp) {
			compareResult.fail("Exception Message => " + exp.getMessage());
		}
		return compareResult;
	}

	public void compareJSON(String arg0, JSONObject arg1, JSONObject arg2, JSONCompareResult arg3)
			throws JSONException {
		// TODO Auto-generated method stub

	}

	public void compareJSONArray(String arg0, JSONArray arg1, JSONArray arg2, JSONCompareResult arg3)
			throws JSONException {
		// TODO Auto-generated method stub

	}

	public void compareValues(String arg0, Object arg1, Object arg2, JSONCompareResult arg3) throws JSONException {
		// TODO Auto-generated method stub

	}

}
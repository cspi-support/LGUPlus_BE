package lguplus_rebm;

import static com.tibco.be.model.functions.FunctionDomain.ACTION;
import static com.tibco.be.model.functions.FunctionDomain.CONDITION;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static com.tibco.be.model.functions.FunctionDomain.BUI;

import com.tibco.be.model.functions.BEFunction;
import com.tibco.be.model.functions.BEMapper;
import com.tibco.be.model.functions.BEPackage;
import com.tibco.be.model.functions.FunctionParamDescriptor;


@BEPackage(
		catalog = "EBM",//Add a catalog name here
		category = "PersistentData", //Add a category name here
		synopsis = "custom catalog function for PersistentData") //Add a synopsis here

public class PersistentData {
    public static HashMap<String,Set<String>> persistentData= new HashMap<String,Set<String>>();

    @BEFunction(
			name = "addData",
			signature = "void addData(String key, String data)",
			params = {
					@FunctionParamDescriptor(
							name = "key", 
							type = "String", 
							desc = "key" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "data", 
							type = "String", 
							desc = "data" /*Add Description here*/),
			},
			freturn = @FunctionParamDescriptor(
							name = "", 
							type = "void", 
							desc = "" /*Add Description here*/
			),
			version = "1.0", /*Add Version here*/
			see = "",
			mapper = @BEMapper(),
			description = "" /*Add Description here*/,
			cautions = "none",
			fndomain = {ACTION, BUI},
			example = ""
			)
    public static void addData(String key, String data) {
    	if(data == null || data.equals("")) return;
        if(persistentData.containsKey(key)) {
            persistentData.get(key).add(data);
        }
        else {
            Set<String> hashSet = new HashSet<String>();
            hashSet.add(data);
            persistentData.put(key, hashSet);
        }
    }
    
    @BEFunction(
			name = "addDatas",
			signature = "void addDatas(String key, String[] datas)",
			params = {
					@FunctionParamDescriptor(
							name = "key", 
							type = "String", 
							desc = "key" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "datas", 
							type = "String[]", 
							desc = "datas" /*Add Description here*/),
			},
			freturn = @FunctionParamDescriptor(
							name = "", 
							type = "void", 
							desc = "" /*Add Description here*/
			),
			version = "1.0", /*Add Version here*/
			see = "",
			mapper = @BEMapper(),
			description = "" /*Add Description here*/,
			cautions = "none",
			fndomain = {ACTION, BUI},
			example = ""
			)
    public static void addDatas(String key, String[] datas) {
    	if(datas == null || datas.length == 0) return;
        if(persistentData.containsKey(key)) {
        	Collections.addAll(persistentData.get(key), datas);
        }
        else {
            Set<String> hashSet = new HashSet<>(Arrays.asList(datas));
            persistentData.put(key, hashSet);
        }
    }
    
    @BEFunction(
			name = "isContained",
			signature = "boolean isContained(String key, String data)",
			params = {
					@FunctionParamDescriptor(
							name = "key", 
							type = "String", 
							desc = "key" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "data", 
							type = "String", 
							desc = "data" /*Add Description here*/),
			},
			freturn = @FunctionParamDescriptor(
							name = "", 
							type = "boolean", 
							desc = "is data included in datas by key." /*Add Description here*/
			),
			version = "1.0", /*Add Version here*/
			see = "",
			mapper = @BEMapper(),
			description = "" /*Add Description here*/,
			cautions = "none",
			fndomain = {ACTION, BUI},
			example = ""
			)
    public static boolean isContained(String key, String data) {
    	if(data == null || data.equals("")) return false;
        if(persistentData.containsKey(key)) {
        	return persistentData.get(key).contains(data);
        }
        else {
        	return false;
        }
    }

    @BEFunction(
			name = "isAnyContained",
			signature = "boolean isContained(String key, String[] datas)",
			params = {
					@FunctionParamDescriptor(
							name = "key", 
							type = "String", 
							desc = "key" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "datas", 
							type = "String[]", 
							desc = "datas" /*Add Description here*/),
			},
			freturn = @FunctionParamDescriptor(
							name = "", 
							type = "boolean", 
							desc = "is data included in datas by key." /*Add Description here*/
			),
			version = "1.0", /*Add Version here*/
			see = "",
			mapper = @BEMapper(),
			description = "" /*Add Description here*/,
			cautions = "none",
			fndomain = {ACTION, BUI},
			example = ""
			)
    public static boolean isAnyContained(String key, String[] datas) {
    	if(datas == null || datas.length == 0) return false;
        if(persistentData.containsKey(key)) {
        	Set<String> dataSet = persistentData.get(key);
        	return Arrays.stream(datas).anyMatch(item -> dataSet.contains(item));
        }
        else {
        	return false;
        }
    }


    @BEFunction(
			name = "getMatchedDatas",
			signature = "String[] getMatchedDatas(String key, String[] datas)",
			params = {
					@FunctionParamDescriptor(
							name = "key", 
							type = "String", 
							desc = "key" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "datas", 
							type = "String[]", 
							desc = "datas" /*Add Description here*/),
			},
			freturn = @FunctionParamDescriptor(
							name = "", 
							type = "String[]", 
							desc = "Matched String array" /*Add Description here*/
			),
			version = "1.0", /*Add Version here*/
			see = "",
			mapper = @BEMapper(),
			description = "" /*Add Description here*/,
			cautions = "none",
			fndomain = {ACTION, BUI},
			example = ""
			)
    public static String[] getMatchedDatas(String key, String[] datas) {
    	if(datas == null || datas.length == 0) return null;
        if(persistentData.containsKey(key)) {
        	Set<String> dataSet = persistentData.get(key);
			return Arrays.stream(datas).filter(item -> dataSet.contains(item)).toArray(String[]::new);
        }
        else {
        	return null;
        }
    }
    
    
    @BEFunction(
			name = "cleanDatas",
			signature = "void cleanDatas(String key)",
			params = {
					@FunctionParamDescriptor(
							name = "key", 
							type = "String", 
							desc = "key" /*Add Description here*/),
			},
			freturn = @FunctionParamDescriptor(
							name = "", 
							type = "void", 
							desc = "" /*Add Description here*/
			),
			version = "1.0", /*Add Version here*/
			see = "",
			mapper = @BEMapper(),
			description = "" /*Add Description here*/,
			cautions = "none",
			fndomain = {ACTION, BUI},
			example = ""
			)
    public static void cleanDatas(String key) {
        if(persistentData.containsKey(key)) {
        	Set<String> datas = persistentData.remove(key);
        	datas.clear();
        	return;
        }
    }
}

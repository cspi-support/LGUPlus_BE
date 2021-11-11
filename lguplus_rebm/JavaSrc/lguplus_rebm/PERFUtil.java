package lguplus_rebm;

import static com.tibco.be.model.functions.FunctionDomain.ACTION;
import static com.tibco.be.model.functions.FunctionDomain.CONDITION;
import static com.tibco.be.model.functions.FunctionDomain.BUI;

import com.tibco.be.model.functions.BEFunction;
import com.tibco.be.model.functions.BEMapper;
import com.tibco.be.model.functions.BEPackage;
import com.tibco.be.model.functions.FunctionParamDescriptor;
import com.tibco.as.space.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@BEPackage(
		catalog = "EBM",//Add a catalog name here
		category = "PERFUtil", //Add a category name here
		synopsis = "custom catalog function for PERFUtil") //Add a synopsis here
public class PERFUtil{
    public static HashMap<String,ArrayList<String>> conceptExtMapByKey = new HashMap<String, ArrayList<String>>();
	@BEFunction(
			name = "threadSleep",
			signature = "void threadSleep(long sleepTime)",
			params = {
					@FunctionParamDescriptor(
							name = "sleepTime", 
							type = "long", 
							desc = "" /*Add Description here*/),
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
    public static void threadSleep(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            // no action
        }
    }
	
   /**
     * Concept ID를 HashMap 에 관리
     * @param conceptId
     */
    @BEFunction(
			name = "getCurrentThreadId",
			signature = "long getCurrentThreadId()",
			params = {
					@FunctionParamDescriptor(
							name = "", 
							type = "", 
							desc = "" /*Add Description here*/),
			},
			freturn = @FunctionParamDescriptor(
							name = "threadId", 
							type = "long", 
							desc = "currentThread id" /*Add Description here*/
			),
			version = "1.0", /*Add Version here*/
			see = "",
			mapper = @BEMapper(),
			description = "" /*Add Description here*/,
			cautions = "none",
			fndomain = {ACTION, CONDITION, BUI},
			example = ""
			)
    public static long getCurrentThreadId() {
    	return Thread.currentThread().getId();
    }

    public static HashMap<Long,ArrayList<Pair<String,Long>>> timeMap = new HashMap<Long,ArrayList<Pair<String,Long>>>();
    /**
     * performance 체크를 위해 시간 정보를 저장
     * @param conceptId
     */
    @BEFunction(
			name = "addTimeData",
			signature = "void addTimeData(String tag, long currentTime)",
			params = {
					@FunctionParamDescriptor(
							name = "tag", 
							type = "String", 
							desc = "tag for time " /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "currentTime", 
							type = "long", 
							desc = "current time Millis " /*Add Description here*/),
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
			fndomain = {ACTION, CONDITION, BUI},
			example = ""
			)
    public static void addTimeData(String tag, long currentTime) {
        if(timeMap.containsKey(Thread.currentThread().getId())) {
        	Pair<String, Long> pair =  new Pair<String,Long>(tag, currentTime);
            timeMap.get(Thread.currentThread().getId()).add(pair);
        }
        else {
            ArrayList<Pair<String,Long>> timeList = new ArrayList<Pair<String,Long>>();
        	Pair<String, Long> pair =  new Pair<String,Long>(tag, currentTime);
            timeList.add(pair);
            timeMap.put(Thread.currentThread().getId(), timeList);
        }
    }

    /**
     * 관리하던 time Data를 HashMap에서 조회하여 Return
     * @return
     */
    @BEFunction(
			name = "takeTimeDatas",
			signature = "String[] takeTimeDatas()",
			params = {
					@FunctionParamDescriptor(
							name = "", 
							type = "", 
							desc = "" /*Add Description here*/),
			},
			freturn = @FunctionParamDescriptor(
							name = "timedata array", 
							type = "String[]", 
							desc = "all the time datas that were created at RTC time" /*Add Description here*/
			),
			version = "1.0", /*Add Version here*/
			see = "",
			mapper = @BEMapper(),
			description = "" /*Add Description here*/,
			cautions = "none",
			fndomain = {ACTION, CONDITION, BUI},
			example = ""
			)
    public static String[] takeTimeDatas() {
    	long id = Thread.currentThread().getId();
        if(timeMap.containsKey(id)) {
            ArrayList<Pair<String,Long>> timeList = timeMap.get(id);
            String[] timeDatas = new String[timeList.size()];
            for(int i = 0;i<timeList.size();i++) {
            	Pair<String,Long> data = timeList.get(i);
                timeDatas[i] = "" + data.getFirst() + "=" + data.getSecond();
            }
            timeList.clear();
            return timeDatas;
        }
        return null;
    }

    public static HashMap<String,ArrayList<Pair<String,Long>>> timeKeyMap = new HashMap<String,ArrayList<Pair<String,Long>>>();
    /**
     * performance 체크를 위해 시간 정보를 저장
     * @param conceptId
     */
    @BEFunction(
			name = "addTimeDataByKey",
			signature = "void addTimeDataByKey(String key, String tag, long currentTime)",
			params = {
					@FunctionParamDescriptor(
							name = "key", 
							type = "String", 
							desc = "key for time " /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "tag", 
							type = "String", 
							desc = "tag for time " /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "currentTime", 
							type = "long", 
							desc = "current time Millis " /*Add Description here*/),
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
			fndomain = {ACTION, CONDITION, BUI},
			example = ""
			)
    public static void addTimeDataByKey(String key, String tag, long currentTime) {
        if(timeKeyMap.containsKey(key)) {
        	Pair<String, Long> pair =  new Pair<String,Long>(tag, currentTime);
            timeKeyMap.get(key).add(pair);
        }
        else {
            ArrayList<Pair<String,Long>> timeList = new ArrayList<Pair<String,Long>>();
        	Pair<String, Long> pair =  new Pair<String,Long>(tag, currentTime);
            timeList.add(pair);
            timeKeyMap.put(key, timeList);
        }
    }

    /**
     * 관리하던 time Data를 HashMap에서 조회하여 Return
     * @return
     */
    @BEFunction(
			name = "takeTimeDatasByKey",
			signature = "String[] takeTimeDatasByKey(String key)",
			params = {
					@FunctionParamDescriptor(
							name = "key", 
							type = "String", 
							desc = "key for map" /*Add Description here*/),
			},
			freturn = @FunctionParamDescriptor(
							name = "timedata array", 
							type = "String[]", 
							desc = "all the time datas that were created at RTC time" /*Add Description here*/
			),
			version = "1.0", /*Add Version here*/
			see = "",
			mapper = @BEMapper(),
			description = "" /*Add Description here*/,
			cautions = "none",
			fndomain = {ACTION, CONDITION, BUI},
			example = ""
			)
    public static String[] takeTimeDatasByKey(String key) {
        if(timeKeyMap.containsKey(key)) {
            ArrayList<Pair<String,Long>> timeList = timeKeyMap.get(key);
            String[] timeDatas = new String[timeList.size()];
            for(int i = 0;i<timeList.size();i++) {
            	Pair<String,Long> data = timeList.get(i);
                timeDatas[i] = "" + data.getFirst() + "=" + data.getSecond();
            }
            timeList.clear();
            return timeDatas;
        }
        return null;
    }

    @BEFunction(
			name = "addConceptExtIdByKey",
			signature = "void addConceptExtId(String key, String extId)",
			params = {
					@FunctionParamDescriptor(
							name = "key", 
							type = "String", 
							desc = "key" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "extId", 
							type = "String", 
							desc = "concept extId that is created at RTC time" /*Add Description here*/),
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
    public static void addConceptExtIdByKey(String key, String extId) {
        if(conceptExtMapByKey.containsKey(key)) {
            conceptExtMapByKey.get(key).add(extId);
        }
        else {
            ArrayList<String> conceptList = new ArrayList<String>();
            conceptList.add(extId);
            conceptExtMapByKey.put(key, conceptList);
        }
    }
    
    @BEFunction(
			name = "getConceptExtIdsByKey",
			signature = "String[] getConceptExtIdsByKey(String key)",
			params = {
					@FunctionParamDescriptor(
							name = "key", 
							type = "String", 
							desc = "key" /*Add Description here*/),
			},
			freturn = @FunctionParamDescriptor(
							name = "conceptExtIds array", 
							type = "String[]", 
							desc = "all the conceptIds that were created at RTC time" /*Add Description here*/
			),
			version = "1.0", /*Add Version here*/
			see = "",
			mapper = @BEMapper(),
			description = "" /*Add Description here*/,
			cautions = "none",
			fndomain = {ACTION, BUI},
			example = ""
			)
    public static String[] getConceptExtIdsByKey(String key) {
        if(conceptExtMapByKey.containsKey(key)) {
            ArrayList<String> conceptIdList = conceptExtMapByKey.get(key);
            String[] conceptIds = new String[conceptIdList.size()];
            for(int i = 0;i<conceptIdList.size();i++) {
                conceptIds[i] = conceptIdList.get(i);
            }
            conceptIdList.clear();
            return conceptIds;
        }
        return null;
    }
}

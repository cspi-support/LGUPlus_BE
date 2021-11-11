package lguplus_rebm;

import static com.tibco.be.model.functions.FunctionDomain.ACTION;
import static com.tibco.be.model.functions.FunctionDomain.BUI;

import com.tibco.be.model.functions.BEFunction;
import com.tibco.be.model.functions.BEMapper;
import com.tibco.be.model.functions.BEPackage;
import com.tibco.be.model.functions.FunctionParamDescriptor;

import com.tibco.as.space.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Eric
 * Date: 13. 7. 11 
 * Time: 오전 9:08
 */
@BEPackage(
		catalog = "EBM",//Add a catalog name here
		category = "ASUtil", //Add a category name here
		synopsis = "custom catalog function for SHINHAN Project: add functions to handle something") //Add a synopsis here
public class ASUtil{
    private static Map<String, Space> spaceMap = new HashMap<String, Space>();

	@BEFunction(
			name = "getLockOption",
			signature = "Object getLockOption(long lockTimeWait)",
			params = {
					@FunctionParamDescriptor(
							name = "lockTimeWait", 
							type = "long", 
							desc = "" /*Add Description here*/)
			},
			freturn = @FunctionParamDescriptor(
							name = "", 
							type = "Object", 
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
	public static Object getLockOption(long lockTimeWait) 
	{
		LockOptions opt = LockOptions.create();
		opt.setLockWait(lockTimeWait);
        return opt;
    }
	
	@BEFunction(
			name = "getAll",
			signature = "Object getAll(String metaspaceName, String spaceName, Object tupleList)",
			params = {
					@FunctionParamDescriptor(
							name = "metaspaceName", 
							type = "String", 
							desc = "" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "spaceName", 
							type = "String", 
							desc = "" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "tupleList", 
							type = "Object", 
							desc = "" /*Add Description here*/)
			},
			freturn = @FunctionParamDescriptor(
							name = "", 
							type = "Object", 
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
    public static Object getAll(String metaspaceName, String spaceName, Object tupleList) {
        Space space = (Space) getSpace(metaspaceName, spaceName);
        if(space == null) return null;

        @SuppressWarnings("unchecked")
		SpaceResultList resultList = space.getAll((Collection<Tuple>) tupleList);
        if(resultList == null) return null;
        else return resultList.getTuples();
    }

	@SuppressWarnings("unchecked")
	@BEFunction(
			name = "getItems",
			signature = "Object getItems(String metaspaceName, String spaceName, Object tupleParam)",
			params = {
					@FunctionParamDescriptor(
							name = "metaspaceName", 
							type = "String", 
							desc = "" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "spaceName", 
							type = "String", 
							desc = "" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "tupleParam", 
							type = "Object", 
							desc = "" /*Add Description here*/)
			},
			freturn = @FunctionParamDescriptor(
							name = "", 
							type = "Object", 
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
    public static Object getItems(String metaspaceName, String spaceName, Object tupleParam) {
        Space space = (Space) getSpace(metaspaceName, spaceName);
        if(space == null) return null;

        ArrayList<Tuple> param = null;
        if(tupleParam instanceof Tuple) {
        	param = new ArrayList<Tuple>();
        	param.add((Tuple) tupleParam);
        } else {
        	param = (ArrayList<Tuple>) tupleParam;
        }
		SpaceResultList resultList = space.getAll(param);
        if(resultList == null) return null;
        return resultList.getTuples();
    }
	
	
	@BEFunction(
			name = "takeForget",
			signature = "void takeForget(String metaspaceName, String spaceName, Object tuple)",
			params = {
					@FunctionParamDescriptor(
							name = "metaspaceName", 
							type = "String", 
							desc = "" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "spaceName", 
							type = "String", 
							desc = "" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "tuple", 
							type = "Object", 
							desc = "" /*Add Description here*/)
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
    public static void takeForget(String metaspaceName, String spaceName, Object tuple) throws RuntimeException{
        Space space = (Space) getSpace(metaspaceName, spaceName);
        TakeOptions takeOptions = TakeOptions.create();
        takeOptions.setForget(true);
        try {
            space.take((Tuple) tuple,takeOptions);
        } catch (Exception e) {
            new RuntimeException("TakeForget fail", e);
        }
    }

	@BEFunction(
			name = "putForget",
			signature = "void putForget(String metaspaceName, String spaceName, Object tuple)",
			params = {
					@FunctionParamDescriptor(
							name = "metaspaceName", 
							type = "String", 
							desc = "" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "spaceName", 
							type = "String", 
							desc = "" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "tuple", 
							type = "Object", 
							desc = "" /*Add Description here*/)
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
    public static void putForget(String metaspaceName, String spaceName, Object tuple) throws RuntimeException{
        Space space = (Space) getSpace(metaspaceName, spaceName);
        PutOptions putOptions = PutOptions.create();
        putOptions.setForget(true);
        try {
            space.put((Tuple) tuple,putOptions);
        } catch (Exception e) {
            new RuntimeException("TakeForget fail", e);
        }
    }
	
    private static Object getSpace(String metaspaceName, String spaceName) {
        if(spaceMap.containsKey(spaceName)) return spaceMap.get(spaceName);
        else {
            try {
                spaceMap.put(spaceName, ASCommon.getMetaspace(metaspaceName).getSpace(spaceName));
                return spaceMap.get(spaceName);
            } catch (Exception e) {
                return null;
            }
        }
    }
    
	@BEFunction(
			name = "putAll",
			signature = "Object putAll(String metaspaceName, String spaceName, Object tupleList)",
			params = {
					@FunctionParamDescriptor(
							name = "metaspaceName", 
							type = "String", 
							desc = "" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "spaceName", 
							type = "String", 
							desc = "" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "tupleList", 
							type = "Object", 
							desc = "" /*Add Description here*/)
			},
			freturn = @FunctionParamDescriptor(
							name = "", 
							type = "Object", 
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
    public static Object putAll(String metaspaceName, String spaceName, Object tupleList) {
        Space space = (Space) getSpace(metaspaceName, spaceName);
        if(space == null) return null;

        @SuppressWarnings("unchecked")
		SpaceResultList resultList = space.putAll((Collection<Tuple>) tupleList);
        if(resultList == null) return null;
        else return resultList.getTuples();
    }
	
	@BEFunction(
			name = "compareAndPut",
			signature = "Object compareAndPut(String metaspaceName, String spaceName, Object orgTuple, Object newTuple)",
			params = {
					@FunctionParamDescriptor(
							name = "metaspaceName", 
							type = "String", 
							desc = "" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "spaceName", 
							type = "String", 
							desc = "" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "orgTuple", 
							type = "Object", 
							desc = "" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "newTuple", 
							type = "Object", 
							desc = "" /*Add Description here*/)
			},
			freturn = @FunctionParamDescriptor(
							name = "", 
							type = "Object", 
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
    public static Object compareAndPut(String metaspaceName, String spaceName, Object orgTuple, Object newTuple) {
        try {
			Space space = (Space) getSpace(metaspaceName, spaceName);
			if(space == null) return null;

			@SuppressWarnings("unchecked")
			Tuple result = space.compareAndPut((Tuple)orgTuple, (Tuple)newTuple);
			if(result == null) return null;
			else return result;
        } catch (Exception e) {
            return null;
        }
    }
	
	@BEFunction(
			name = "compareAndPutAll",
			signature = "Object compareAndPutAll(String metaspaceName, String spaceName, Object orgTupleList, object newTupleList)",
			params = {
					@FunctionParamDescriptor(
							name = "metaspaceName", 
							type = "String", 
							desc = "" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "spaceName", 
							type = "String", 
							desc = "" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "orgTupleList", 
							type = "Object", 
							desc = "" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "newTupleList", 
							type = "Object", 
							desc = "" /*Add Description here*/)
			},
			freturn = @FunctionParamDescriptor(
							name = "", 
							type = "Object", 
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
    public static Object compareAndPutAll(String metaspaceName, String spaceName, Object orgTupleList, Object newTupleList) {
        Space space = (Space) getSpace(metaspaceName, spaceName);
        if(space == null) return null;

        @SuppressWarnings("unchecked")
		SpaceResultList resultList = space.compareAndPutAll((Collection<Tuple>) orgTupleList, (Collection<Tuple>) newTupleList);
        if(resultList == null) return null;
        else return resultList.getTuples();
    }
	
}

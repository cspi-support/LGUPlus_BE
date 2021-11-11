package lguplus_rebm;

import static com.tibco.be.model.functions.FunctionDomain.ACTION;
import static com.tibco.be.model.functions.FunctionDomain.CONDITION;
import static com.tibco.be.model.functions.FunctionDomain.BUI;

import com.tibco.be.model.functions.BEFunction;
import com.tibco.be.model.functions.BEMapper;
import com.tibco.be.model.functions.BEPackage;
import com.tibco.be.model.functions.FunctionParamDescriptor;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@BEPackage(
		catalog = "EBM",//Add a catalog name here
		category = "LguplusUtil", //Add a category name here
		synopsis = "custom catalog function for Project: add functions to handle something") //Add a synopsis here
public class LguplusUtil {
	
	@BEFunction(
			name = "contains",
			signature = "boolean contains(String[] array, String item)",
			params = {
					@FunctionParamDescriptor(
							name = "array", 
							type = "String[]", 
							desc = "Array" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "item", 
							type = "String", 
							desc = "item in Array" /*Add Description here*/)
			},
			freturn = @FunctionParamDescriptor(
							name = "", 
							type = "boolean", 
							desc = "contains : true else false" /*Add Description here*/
			),
			version = "1.0", /*Add Version here*/
			see = "",
			mapper = @BEMapper(),
			description = "" /*Add Description here*/,
			cautions = "none",
			fndomain = {ACTION, CONDITION, BUI},
			example = ""
			)
	public static boolean contains(String[] array, String item) {
		return Arrays.stream(array).anyMatch(item::equals);
	}

	@BEFunction(
			name = "isEmpty",
			signature = "boolean isEmpty(String value)",
			params = {
					@FunctionParamDescriptor(
							name = "value", 
							type = "String", 
							desc = "string for checking" /*Add Description here*/)
			},
			freturn = @FunctionParamDescriptor(
							name = "", 
							type = "boolean", 
							desc = "true : null or empty" /*Add Description here*/
			),
			version = "1.0", /*Add Version here*/
			see = "",
			mapper = @BEMapper(),
			description = "" /*Add Description here*/,
			cautions = "none",
			fndomain = {ACTION, CONDITION, BUI},
			example = ""
			)
	public static boolean isEmpty(String value) {
		if(value == null || value.length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	@BEFunction(
			name = "intersects",
			signature = "String[] intersects(String firstSet, String secondSet, String delimiter)",
			params = {
					@FunctionParamDescriptor(
							name = "firstSet", 
							type = "String", 
							desc = "first delimited string" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "secondSet", 
							type = "String", 
							desc = "second delimited string" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "delimiter", 
							type = "String", 
							desc = "tokenizing delimiter" /*Add Description here*/)
			},
			freturn = @FunctionParamDescriptor(
							name = "", 
							type = "String", 
							desc = "intersected string" /*Add Description here*/
			),
			version = "1.0", /*Add Version here*/
			see = "",
			mapper = @BEMapper(),
			description = "" /*Add Description here*/,
			cautions = "none",
			fndomain = {ACTION, CONDITION, BUI},
			example = ""
			)
	public static String[] intersects(String firstSet, String secondSet, String delimiter) {
		String[] firstArray = (isEmpty(firstSet))? null : firstSet.split(delimiter);
		String[] secondArray = (isEmpty(secondSet))? null : secondSet.split(delimiter);
		
		if(firstArray == null || secondArray == null) return null;

		Set<String> firstS = (firstArray != null)? new HashSet<String>(Arrays.asList(firstArray)) : new HashSet<String>();
		Set<String> secondS = (secondArray != null)? new HashSet<String>(Arrays.asList(secondArray)) : new HashSet<String>();
		firstS.retainAll(secondS);

		if(firstS.isEmpty()) return null;
		return firstS.toArray(new String[firstS.size()]);
	}

	@BEFunction(
			name = "intersectsWith",
			signature = "String[] intersectsWith(String[] firstArray, String secondSet, String delimiter)",
			params = {
					@FunctionParamDescriptor(
							name = "firstArray", 
							type = "String[]", 
							desc = "first Array" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "secondSet", 
							type = "String", 
							desc = "second delimited string" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "delimiter", 
							type = "String", 
							desc = "tokenizing delimiter" /*Add Description here*/)
			},
			freturn = @FunctionParamDescriptor(
							name = "", 
							type = "String", 
							desc = "intersected string" /*Add Description here*/
			),
			version = "1.0", /*Add Version here*/
			see = "",
			mapper = @BEMapper(),
			description = "" /*Add Description here*/,
			cautions = "none",
			fndomain = {ACTION, CONDITION, BUI},
			example = ""
			)
	public static String[] intersectsWith(String[] firstArray, String secondSet, String delimiter) {
		String[] secondArray = (isEmpty(secondSet))? null : secondSet.split(delimiter);
		
		if(firstArray == null || secondArray == null) return null;

		Set<String> firstS = (firstArray != null)? new HashSet<String>(Arrays.asList(firstArray)) : new HashSet<String>();
		Set<String> secondS = (secondArray != null)? new HashSet<String>(Arrays.asList(secondArray)) : new HashSet<String>();
		firstS.retainAll(secondS);

		if(firstS.isEmpty()) return null;
		return firstS.toArray(new String[firstS.size()]);
	}

	@BEFunction(
			name = "unionArray",
			signature = "String[] unionArray(String[] firstArray, String[] secondArray)",
			params = {
					@FunctionParamDescriptor(
							name = "firstArray", 
							type = "String[]", 
							desc = "first string Array" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "secondArray", 
							type = "String[]", 
							desc = "second string Array" /*Add Description here*/)
			},
			freturn = @FunctionParamDescriptor(
							name = "", 
							type = "String[]", 
							desc = "Union string Array" /*Add Description here*/
			),
			version = "1.0", /*Add Version here*/
			see = "",
			mapper = @BEMapper(),
			description = "" /*Add Description here*/,
			cautions = "none",
			fndomain = {ACTION, CONDITION, BUI},
			example = ""
			)
	public static String[] unionArray(String[] firstArray, String[] secondArray) {
		if(firstArray == null) return secondArray;
		if(secondArray == null) return firstArray;

		Set<String> firstS = (firstArray != null)? new HashSet<String>(Arrays.asList(firstArray)) : new HashSet<String>();
		Set<String> secondS = (secondArray != null)? new HashSet<String>(Arrays.asList(secondArray)) : new HashSet<String>();
		firstS.addAll(secondS);
		return firstS.toArray(new String[firstS.size()]);
	}

	@BEFunction(
			name = "removeArray",
			signature = "String removeArray(String[] targetArray, String[] deleteArray)",
			params = {
					@FunctionParamDescriptor(
							name = "targetArray", 
							type = "String[]", 
							desc = "target string array" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "deleteArray", 
							type = "String[]", 
							desc = "element Array for deleting" /*Add Description here*/)
			},
			freturn = @FunctionParamDescriptor(
							name = "", 
							type = "String[]", 
							desc = "removed string Array" /*Add Description here*/
			),
			version = "1.0", /*Add Version here*/
			see = "",
			mapper = @BEMapper(),
			description = "" /*Add Description here*/,
			cautions = "none",
			fndomain = {ACTION, CONDITION, BUI},
			example = ""
			)
	public static String[] removeArray(String[] targetArray, String[] deleteArray) {
		if(targetArray == null) return null;
		if(deleteArray == null) return targetArray;

		Set<String> firstS = (targetArray != null)? new HashSet<String>(Arrays.asList(targetArray)) : new HashSet<String>();
		Set<String> secondS = (deleteArray != null)? new HashSet<String>(Arrays.asList(deleteArray)) : new HashSet<String>();
		firstS.removeAll(secondS);

		if(firstS.isEmpty()) return null;
		return firstS.toArray(new String[firstS.size()]);
	}

	@BEFunction(
			name = "intersectArray",
			signature = "String[] intersectArray(String[] firstArray, String[] secondArray)",
			params = {
					@FunctionParamDescriptor(
							name = "firstArray", 
							type = "String[]", 
							desc = "first string array" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "secondArray", 
							type = "String[]", 
							desc = "second string array" /*Add Description here*/)
			},
			freturn = @FunctionParamDescriptor(
							name = "", 
							type = "String[]", 
							desc = "intersected string array" /*Add Description here*/
			),
			version = "1.0", /*Add Version here*/
			see = "",
			mapper = @BEMapper(),
			description = "" /*Add Description here*/,
			cautions = "none",
			fndomain = {ACTION, CONDITION, BUI},
			example = ""
			)
	public static String[] intersectArray(String[] firstArray, String[] secondArray) {
		if(firstArray == null || secondArray == null) return null;

		Set<String> firstS = (firstArray != null)? new HashSet<String>(Arrays.asList(firstArray)) : new HashSet<String>();
		Set<String> secondS = (secondArray != null)? new HashSet<String>(Arrays.asList(secondArray)) : new HashSet<String>();
		firstS.retainAll(secondS);

		if(firstS.isEmpty()) return null;
		return firstS.toArray(new String[firstS.size()]);
	}

	@BEFunction(
			name = "isSubArray",
			signature = "boolean isSubArray(String[] targetArray, String[] checkArray)",
			params = {
					@FunctionParamDescriptor(
							name = "targetArray", 
							type = "String[]", 
							desc = "target string array" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "checkArray", 
							type = "String[]", 
							desc = "check string array" /*Add Description here*/)
			},
			freturn = @FunctionParamDescriptor(
							name = "", 
							type = "boolean", 
							desc = "true if targetSet contains all checkSet else false" /*Add Description here*/
			),
			version = "1.0", /*Add Version here*/
			see = "",
			mapper = @BEMapper(),
			description = "" /*Add Description here*/,
			cautions = "none",
			fndomain = {ACTION, CONDITION, BUI},
			example = ""
			)
	public static boolean isSubArray(String[] targetArray, String[] checkArray) {
		if(targetArray == null || checkArray == null) return false;

		Set<String> firstS = (targetArray != null)? new HashSet<String>(Arrays.asList(targetArray)) : new HashSet<String>();
		Set<String> secondS = (checkArray != null)? new HashSet<String>(Arrays.asList(checkArray)) : new HashSet<String>();
		return firstS.containsAll(secondS);
	}

	@BEFunction(
			name = "isContainAny",
			signature = "boolean isContainAny(String[] targetArray, String[] checkArray)",
			params = {
					@FunctionParamDescriptor(
							name = "targetArray", 
							type = "String[]", 
							desc = "target string array" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "checkArray", 
							type = "String[]", 
							desc = "check string array" /*Add Description here*/)
			},
			freturn = @FunctionParamDescriptor(
							name = "", 
							type = "boolean", 
							desc = "true if targetSet contains at least one of checkSet else false" /*Add Description here*/
			),
			version = "1.0", /*Add Version here*/
			see = "",
			mapper = @BEMapper(),
			description = "" /*Add Description here*/,
			cautions = "none",
			fndomain = {ACTION, CONDITION, BUI},
			example = ""
			)
	public static boolean isContainAny(String[] targetArray, String[] checkArray) {
		if(targetArray == null || checkArray == null) return false;

		boolean result = Arrays.stream(checkArray).anyMatch(item -> contains(targetArray, item));
		return result;
	}


	@BEFunction(
			name = "InFilteredArray",
			signature = "String[] InFilteredArray(String[] targetArray, String[] inFilter)",
			params = {
					@FunctionParamDescriptor(
							name = "targetArray", 
							type = "String[]", 
							desc = "target string array" /*Add Description here*/),
					@FunctionParamDescriptor(
							name = "inFilter", 
							type = "String[]", 
							desc = "string array for FILTER-IN" /*Add Description here*/)
			},
			freturn = @FunctionParamDescriptor(
							name = "", 
							type = "String[]", 
							desc = "filtered string array" /*Add Description here*/
			),
			version = "1.0", /*Add Version here*/
			see = "",
			mapper = @BEMapper(),
			description = "" /*Add Description here*/,
			cautions = "none",
			fndomain = {ACTION, CONDITION, BUI},
			example = ""
			)
	public static String[] InFilteredArray(String[] targetArray, String[] inFilter) {
		if(targetArray== null || inFilter == null) return null;
		return Arrays.stream(targetArray).filter(item -> contains(inFilter, item)).toArray(String[]::new);
	}
}

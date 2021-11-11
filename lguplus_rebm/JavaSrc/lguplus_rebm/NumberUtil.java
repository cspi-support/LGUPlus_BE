package lguplus_rebm;

import static com.tibco.be.model.functions.FunctionDomain.ACTION;
import static com.tibco.be.model.functions.FunctionDomain.CONDITION;
import static com.tibco.be.model.functions.FunctionDomain.BUI;

import com.tibco.be.model.functions.BEFunction;
import com.tibco.be.model.functions.BEMapper;
import com.tibco.be.model.functions.BEPackage;
import com.tibco.be.model.functions.FunctionParamDescriptor;
import com.tibco.cep.studio.core.grammar.concept.Concept_BaseElement.returnStatement_return;


@BEPackage(
		catalog = "EBM",//Add a catalog name here
		category = "NumberUtil", //Add a category name here
		synopsis = "custom catalog function for Project: add functions to handle something") //Add a synopsis here
public class NumberUtil {
	@BEFunction(
			name = "toULong",
			signature = "String toULong(int value)",
			params = {
					@FunctionParamDescriptor(
							name = "value", 
							type = "int", 
							desc = "" /*Add Description here*/)
			},
			freturn = @FunctionParamDescriptor(
							name = "", 
							type = "long", 
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
	public static long toULong(int value) {
		return Integer.toUnsignedLong(value);
	}

	@BEFunction(
			name = "IntToHex",
			signature = "String IntToHex(int value)",
			params = {
					@FunctionParamDescriptor(
							name = "value", 
							type = "int", 
							desc = "" /*Add Description here*/)
			},
			freturn = @FunctionParamDescriptor(
							name = "", 
							type = "String", 
							desc = "Hex String" /*Add Description here*/
			),
			version = "1.0", /*Add Version here*/
			see = "",
			mapper = @BEMapper(),
			description = "" /*Add Description here*/,
			cautions = "none",
			fndomain = {ACTION, CONDITION, BUI},
			example = ""
			)
	public static String IntToHex(int value) {
		return Integer.toHexString(value);
	}

	@BEFunction(
			name = "HexToInt",
			signature = "int HexToInt(String hex)",
			params = {
					@FunctionParamDescriptor(
							name = "hex", 
							type = "String", 
							desc = "" /*Add Description here*/)
			},
			freturn = @FunctionParamDescriptor(
							name = "", 
							type = "int", 
							desc = "int" /*Add Description here*/
			),
			version = "1.0", /*Add Version here*/
			see = "",
			mapper = @BEMapper(),
			description = "" /*Add Description here*/,
			cautions = "none",
			fndomain = {ACTION, CONDITION, BUI},
			example = ""
			)
	public static int HexToInt(String hex) {
		return Integer.parseInt(hex, 16);
	}

	@BEFunction(
			name = "toTest",
			signature = "String toTest(int value)",
			params = {
					@FunctionParamDescriptor(
							name = "value", 
							type = "int", 
							desc = "" /*Add Description here*/)
			},
			freturn = @FunctionParamDescriptor(
							name = "", 
							type = "long", 
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
	public static long toTest(int value) {
		return Integer.toUnsignedLong(value);
	}
}

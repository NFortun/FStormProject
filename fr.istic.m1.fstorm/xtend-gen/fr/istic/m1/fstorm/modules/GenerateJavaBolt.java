package fr.istic.m1.fstorm.modules;

import fr.istic.m1.fstorm.beans.StormComponent;
import gecos.core.ParameterSymbol;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import org.apache.storm.tuple.Tuple;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class GenerateJavaBolt {
  private String packageName;
  
  private String BuilDir;
  
  public GenerateJavaBolt(final String packageName, final String BuildDir) {
    this.packageName = packageName;
    this.BuilDir = this.BuilDir;
  }
  
  public ArrayList<String> ArgToString(final Tuple tuple) {
    ArrayList<String> _xblockexpression = null;
    {
      ArrayList<String> arg = new ArrayList<String>();
      for (int i = 0; (i < tuple.size()); i++) {
        int _size = tuple.size();
        int _minus = (_size - 1);
        boolean _equals = (i == _minus);
        if (_equals) {
          arg.add(tuple.getValue(i).toString());
        } else {
          String _string = tuple.getValue(i).toString();
          String _plus = (_string + ",");
          arg.add(_plus);
        }
      }
      _xblockexpression = arg;
    }
    return _xblockexpression;
  }
  
  public CharSequence GenerateJava(final StormComponent component) {
    CharSequence _xblockexpression = null;
    {
      final EList<ParameterSymbol> p = component.getKernel().listParameters();
      ArrayList<String> param = new ArrayList<String>();
      for (int i = 0; (i < p.size()); i++) {
        String _get = component.getParamTypes().get(i);
        String _plus = (_get + " ");
        String _name = p.get(i).getName();
        String _plus_1 = (_plus + _name);
        param.add(_plus_1);
      }
      ArrayList<String> argToC = new ArrayList<String>();
      for (int i = 0; (i < p.size()); i++) {
        String _get = component.getParamTypes().get(i);
        String _plus = ("(" + _get);
        String _plus_1 = (_plus + ") ");
        String _plus_2 = (_plus_1 + "tuple.get(");
        String _plus_3 = (_plus_2 + Integer.valueOf(i));
        String _plus_4 = (_plus_3 + ")");
        argToC.add(_plus_4);
      }
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package ");
      _builder.append(this.packageName);
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.append("import java.util.Map;");
      _builder.newLine();
      _builder.append("import org.apache.storm.tuple.Fields;");
      _builder.newLine();
      _builder.append("import org.apache.storm.tuple.Values;");
      _builder.newLine();
      _builder.append("import org.apache.storm.task.OutputCollector;");
      _builder.newLine();
      _builder.append("import org.apache.storm.task.TopologyContext;import org.apache.storm.topology.IRichBolt;");
      _builder.newLine();
      _builder.append("import org.apache.storm.topology.OutputFieldsDeclarer;");
      _builder.newLine();
      _builder.append("import org.apache.storm.tuple.Tuple;");
      _builder.newLine();
      _builder.append("class ");
      String _firstUpper = StringExtensions.toFirstUpper(component.getKernelName());
      _builder.append(_firstUpper);
      _builder.append("Bolt implements IRichBolt{");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public OutputCollector collector;");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public native ");
      String _returnType = component.getReturnType();
      _builder.append(_returnType, "\t");
      _builder.append(" ");
      String _kernelName = component.getKernelName();
      _builder.append(_kernelName, "\t");
      _builder.append("(");
      {
        boolean _hasElements = false;
        for(final String arg : param) {
          if (!_hasElements) {
            _hasElements = true;
          } else {
            _builder.appendImmediate(",", "\t");
          }
          _builder.append(arg, "\t");
        }
      }
      _builder.append(");");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("static{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("System.loadLibrary(\"");
      String _kernelName_1 = component.getKernelName();
      _builder.append(_kernelName_1, "\t\t");
      _builder.append("\");");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("public void prepare(Map conf, TopologyContext context, OutputCollector collector){");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("this.collector = collector;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public void execute(Tuple tuple){");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("collector.emit(");
      String _kernelName_2 = component.getKernelName();
      _builder.append(_kernelName_2, "\t\t");
      _builder.append("(");
      {
        boolean _hasElements_1 = false;
        for(final String arg_1 : argToC) {
          if (!_hasElements_1) {
            _hasElements_1 = true;
          } else {
            _builder.appendImmediate(",", "\t\t");
          }
          _builder.append(arg_1, "\t\t");
        }
      }
      _builder.append("));");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public void cleanup(){");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public void declareOutputFiels(OutputDeclarer declarer){");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.newLine();
      _builder.newLine();
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
  
  public String Execute(final StormComponent component) {
    try {
      String _xblockexpression = null;
      {
        System.out.println("Lancement de la génération");
        final CharSequence JavaGenerated = this.GenerateJava(component);
        String _firstUpper = StringExtensions.toFirstUpper(component.getKernelName());
        String _plus = (_firstUpper + "Bolt.java");
        final File file = new File(_plus);
        FileOutputStream _fileOutputStream = new FileOutputStream(file);
        final BufferedOutputStream FileWriter = new BufferedOutputStream(_fileOutputStream);
        FileWriter.write(JavaGenerated.toString().getBytes());
        FileWriter.flush();
        String _absolutePath = file.getAbsolutePath();
        String _plus_1 = ("Chemin du fichier : " + _absolutePath);
        _xblockexpression = InputOutput.<String>println(_plus_1);
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}

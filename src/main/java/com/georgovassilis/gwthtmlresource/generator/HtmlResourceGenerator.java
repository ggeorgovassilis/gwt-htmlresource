package com.georgovassilis.gwthtmlresource.generator;

/**
 * Based on TextResourceGenerator from the GWT project. Original copyright follows.
 *
 *
 * Copyright 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

import java.net.URL;

import com.georgovassilis.gwthtmlresource.client.HtmlResource;
import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.resources.ext.AbstractResourceGenerator;
import com.google.gwt.resources.ext.ResourceContext;
import com.google.gwt.resources.ext.ResourceGeneratorUtil;
import com.google.gwt.resources.ext.SupportsGeneratorResultCaching;
import com.google.gwt.user.rebind.SourceWriter;
import com.google.gwt.user.rebind.StringSourceWriter;
import com.googlecode.htmlcompressor.compressor.HtmlCompressor;

public final class HtmlResourceGenerator extends AbstractResourceGenerator
    implements SupportsGeneratorResultCaching {
  private static final int MAX_STRING_CHUNK = 16383;
  
  HtmlCompressor htmlCompressor;
  
  public HtmlResourceGenerator() {
	  System.out.println("Created HTMLResourceGenerator instance");
	  htmlCompressor = new HtmlCompressor();
	  htmlCompressor.setRemoveHttpProtocol(true);
	  htmlCompressor.setRemoveHttpsProtocol(true);
	  htmlCompressor.setRemoveIntertagSpaces(true);
	  htmlCompressor.setRemoveMultiSpaces(true);
	  htmlCompressor.setRemoveQuotes(true);
	  htmlCompressor.setSimpleBooleanAttributes(true);
  }

  @Override
  public String createAssignment(TreeLogger logger, ResourceContext context,
      JMethod method) throws UnableToCompleteException {
    URL[] resources = ResourceGeneratorUtil.findResources(logger, context,
        method);

    if (resources.length != 1) {
      logger.log(TreeLogger.ERROR, "Exactly one resource must be specified",
          null);
      throw new UnableToCompleteException();
    }

    URL resource = resources[0];

    SourceWriter sw = new StringSourceWriter();
    // Write the expression to create the subtype.
    sw.println("new " + HtmlResource.class.getName() + "() {");
    sw.indent();

    if (!AbstractResourceGenerator.STRIP_COMMENTS) {
      // Convenience when examining the generated code.
      sw.println("// " + resource.toExternalForm());
    }

    sw.println("public String getText() {");
    sw.indent();

    String html = com.google.gwt.dev.util.Util.readURLAsString(resource);
    String toWrite = htmlCompressor.compress(html);
    if (toWrite.length() > MAX_STRING_CHUNK) {
      writeLongString(sw, toWrite);
    } else {
      sw.println("return \"" + Generator.escape(toWrite) + "\";");
    }
    sw.outdent();
    sw.println("}");

    sw.println("public String getName() {");
    sw.indent();
    sw.println("return \"" + method.getName() + "\";");
    sw.outdent();
    sw.println("}");

    sw.outdent();
    sw.println("}");

    return sw.toString();
  }

  /**
   * A single constant that is too long will crash the compiler with an out of
   * memory error. Break up the constant and generate code that appends using a
   * buffer.
   */
  private void writeLongString(SourceWriter sw, String toWrite) {
    sw.println("StringBuilder builder = new StringBuilder();");
    int offset = 0;
    int length = toWrite.length();
    while (offset < length - 1) {
      int subLength = Math.min(MAX_STRING_CHUNK, length - offset);
      sw.print("builder.append(\"");
      sw.print(Generator.escape(toWrite.substring(offset, offset + subLength)));
      sw.println("\");");
      offset += subLength;
    }
    sw.println("return builder.toString();");
  }
}

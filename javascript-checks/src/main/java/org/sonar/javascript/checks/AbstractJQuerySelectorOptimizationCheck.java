/*
 * SonarQube JavaScript Plugin
 * Copyright (C) 2011 SonarSource and Eriks Nukis
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.javascript.checks;

import org.sonar.javascript.model.implementations.SeparatedList;
import org.sonar.javascript.model.interfaces.Tree;
import org.sonar.javascript.model.interfaces.expression.CallExpressionTree;
import org.sonar.javascript.model.interfaces.expression.LiteralTree;

public abstract class AbstractJQuerySelectorOptimizationCheck extends AbstractJQueryCheck {

  protected abstract void visitSelector(String selector, Tree tree);

  @Override
  public void visitCallExpression(CallExpressionTree tree) {
    if (isSelector(tree)){
      SeparatedList<Tree> parameters = tree.arguments().parameters();
      if (parameters.size() == 1 && parameters.get(0).is(Tree.Kind.STRING_LITERAL)) {
        String value = ((LiteralTree) parameters.get(0)).value();
        if (value.length() > 2) {
          value = value.substring(1, value.length() - 1).trim();
          visitSelector(value, tree);
        }
      }
    }
  }

}

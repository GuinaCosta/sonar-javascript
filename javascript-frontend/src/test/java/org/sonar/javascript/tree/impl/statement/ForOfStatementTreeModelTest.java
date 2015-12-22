/*
 * SonarQube JavaScript Plugin
 * Copyright (C) 2011-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
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
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.javascript.tree.impl.statement;

import org.junit.Test;
import org.sonar.javascript.utils.JavaScriptTreeModelTest;
import org.sonar.plugins.javascript.api.tree.Tree.Kind;
import org.sonar.plugins.javascript.api.tree.statement.ForOfStatementTree;

import static org.fest.assertions.Assertions.assertThat;

public class ForOfStatementTreeModelTest extends JavaScriptTreeModelTest {

  @Test
  public void test() throws Exception {
    ForOfStatementTree tree = parse("for ( var a of expression ) { }", Kind.FOR_OF_STATEMENT);

    assertThat(tree.is(Kind.FOR_OF_STATEMENT)).isTrue();
    assertThat(tree.forKeyword().text()).isEqualTo("for");
    assertThat(tree.openParenthesis().text()).isEqualTo("(");
    assertThat(tree.ofKeyword().text()).isEqualTo("of");
    assertThat(expressionToString(tree.expression())).isEqualTo("expression");
    assertThat(tree.closeParenthesis().text()).isEqualTo(")");
    assertThat(tree.statement().is(Kind.BLOCK)).isTrue();
  }

}

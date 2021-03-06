package fitnesse.idea.highlighting

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.{SyntaxHighlighter, SyntaxHighlighterBase, SyntaxHighlighterFactory}
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.tree.IElementType
import fitnesse.idea.lexer.{FitnesseLexer, FitnesseTokenType}

class FitnesseHighlighter extends SyntaxHighlighterBase {

  def getHighlightingLexer = new FitnesseLexer

  override def getTokenHighlights(elementType: IElementType) = {
    elementType match {
      case FitnesseTokenType.WIKI_WORD => Array(FitnesseHighlighter.WIKI_WORD)
      case FitnesseTokenType.TABLE_START | FitnesseTokenType.ROW_END | FitnesseTokenType.CELL_END | FitnesseTokenType.TABLE_END => Array(FitnesseHighlighter.TABLE)
      case FitnesseTokenType.BOLD => Array(FitnesseHighlighter.BOLD)
      case FitnesseTokenType.ITALIC => Array(FitnesseHighlighter.ITALIC)
      case _ => Array.empty

    }
  }
}

object FitnesseHighlighter {
  final val BOLD = TextAttributesKey.createTextAttributesKey("FITNESSE.BOLD", DefaultLanguageHighlighterColors.MARKUP_ENTITY)
  final val ITALIC = TextAttributesKey.createTextAttributesKey("FITNESSE.ITALIC", DefaultLanguageHighlighterColors.MARKUP_TAG)

  final val WIKI_WORD = TextAttributesKey.createTextAttributesKey("FITNESSE.WIKI_WORD", DefaultLanguageHighlighterColors.KEYWORD)
  final val TABLE = TextAttributesKey.createTextAttributesKey("FITNESSE.TABLE", DefaultLanguageHighlighterColors.STRING)
}


class FitnesseSyntaxHighlighterFactory extends SyntaxHighlighterFactory {

  override def  getSyntaxHighlighter(project: Project, virtualFile: VirtualFile): SyntaxHighlighter = new FitnesseHighlighter()
}
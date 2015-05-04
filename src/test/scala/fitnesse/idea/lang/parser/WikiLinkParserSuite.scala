package fitnesse.idea.lang.parser

import fitnesse.idea.lang.lexer.FitnesseTokenType

class WikiLinkParserSuite extends ParserSuite {
  test("Relative reference") {
    assertResult(
      Node(FitnesseElementType.FILE, List(
        Node(WikiLinkElementType.RELATIVE_WIKI_LINK, List(
          Leaf(FitnesseTokenType.WIKI_WORD, "SiblingPage")
        ))
      ))
    ) {
      parse("SiblingPage")
    }
  }

  test("Relative reference with child") {
    assertResult(
      Node(FitnesseElementType.FILE, List(
        Node(WikiLinkElementType.RELATIVE_WIKI_LINK, List(
          Leaf(FitnesseTokenType.WIKI_WORD, "SiblingPage"),
          Leaf(FitnesseTokenType.PERIOD, "."),
          Leaf(FitnesseTokenType.WIKI_WORD, "SiblingsChild")
        ))
      ))
    ) {
      parse("SiblingPage.SiblingsChild")
    }
  }

  test("Absolute reference") {
    assertResult(
      Node(FitnesseElementType.FILE, List(
        Node(WikiLinkElementType.ABSOLUTE_WIKI_LINK, List(
          Leaf(FitnesseTokenType.PERIOD, "."),
          Leaf(FitnesseTokenType.WIKI_WORD, "TopPage")
        ))
      ))
    ) {
      parse(".TopPage")
    }
  }

  test("Absolute reference with child") {
    assertResult(
      Node(FitnesseElementType.FILE, List(
        Node(WikiLinkElementType.ABSOLUTE_WIKI_LINK, List(
          Leaf(FitnesseTokenType.PERIOD, "."),
          Leaf(FitnesseTokenType.WIKI_WORD, "TopPage"),
          Leaf(FitnesseTokenType.PERIOD, "."),
          Leaf(FitnesseTokenType.WIKI_WORD, "TopPageChild")
        ))
      ))
    ) {
      parse(".TopPage.TopPageChild")
    }
  }

  test("Subpage reference") {
    assertResult(
      Node(FitnesseElementType.FILE, List(
        Node(WikiLinkElementType.SUBPAGE_WIKI_LINK, List(
          Leaf(FitnesseTokenType.GT, ">"),
          Leaf(FitnesseTokenType.WIKI_WORD, "ChildPage")
        ))
      ))
    ) {
      parse(">ChildPage")
    }
  }

  test("Subpage reference with child") {
    assertResult(
      Node(FitnesseElementType.FILE, List(
        Node(WikiLinkElementType.SUBPAGE_WIKI_LINK, List(
          Leaf(FitnesseTokenType.GT, ">"),
          Leaf(FitnesseTokenType.WIKI_WORD, "ChildPage"),
          Leaf(FitnesseTokenType.PERIOD, "."),
          Leaf(FitnesseTokenType.WIKI_WORD, "GrandChildPage")
        ))
      ))
    ) {
      parse(">ChildPage.GrandChildPage")
    }
  }

  test("Ancestor reference") {
    assertResult(
      Node(FitnesseElementType.FILE, List(
        Node(WikiLinkElementType.ANCESTOR_WIKI_LINK, List(
          Leaf(FitnesseTokenType.LT, "<"),
          Leaf(FitnesseTokenType.WIKI_WORD, "AncestorPage")
        ))
      ))
    ) {
      parse("<AncestorPage")
    }
  }

  test("Ancestor reference with child") {
    assertResult(
      Node(FitnesseElementType.FILE, List(
        Node(WikiLinkElementType.ANCESTOR_WIKI_LINK, List(
          Leaf(FitnesseTokenType.LT, "<"),
          Leaf(FitnesseTokenType.WIKI_WORD, "AncestorPage"),
          Leaf(FitnesseTokenType.PERIOD, "."),
          Leaf(FitnesseTokenType.WIKI_WORD, "AncestorChildPage")
        ))
      ))
    ) {
      parse("<AncestorPage.AncestorChildPage")
    }
  }

}
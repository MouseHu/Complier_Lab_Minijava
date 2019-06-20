//
// Generated by JTB 1.3.2
//

package symbol.syntaxtree;

/**
 * The interface which all syntax tree classes must implement.
 */
public interface Node extends java.io.Serializable {
   public void accept(symbol.visitor.Visitor v);
   public <R,A> R accept(symbol.visitor.GJVisitor<R,A> v, A argu);
   public <R> R accept(symbol.visitor.GJNoArguVisitor<R> v);
   public <A> void accept(symbol.visitor.GJVoidVisitor<A> v, A argu);
}

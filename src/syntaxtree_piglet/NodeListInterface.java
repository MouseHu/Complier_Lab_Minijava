//
// Generated by JTB 1.3.2
//

package syntaxtree_piglet;

/**
 * The interface which NodeList, NodeListOptional, and NodeSequence
 * implement.
 */
public interface NodeListInterface extends Node {
   public void addNode(Node n);
   public Node elementAt(int i);
   public java.util.Enumeration<Node> elements();
   public int size();

   public void accept(visitor_piglet.Visitor v);
   public <R,A> R accept(visitor_piglet.GJVisitor<R,A> v, A argu);
   public <R> R accept(visitor_piglet.GJNoArguVisitor<R> v);
   public <A> void accept(visitor_piglet.GJVoidVisitor<A> v, A argu);
}


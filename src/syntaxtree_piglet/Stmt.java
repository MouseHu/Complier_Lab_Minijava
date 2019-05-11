//
// Generated by JTB 1.3.2
//

package syntaxtree_piglet;

/**
 * Grammar production:
 * f0 -> NoOpStmt()
 *       | ErrorStmt()
 *       | CJumpStmt()
 *       | JumpStmt()
 *       | HStoreStmt()
 *       | HLoadStmt()
 *       | MoveStmt()
 *       | PrintStmt()
 */
public class Stmt implements Node {
   public NodeChoice f0;

   public Stmt(NodeChoice n0) {
      f0 = n0;
   }

   public void accept(visitor_piglet.Visitor v) {
      v.visit(this);
   }
   public <R,A> R accept(visitor_piglet.GJVisitor<R,A> v, A argu) {
      return v.visit(this,argu);
   }
   public <R> R accept(visitor_piglet.GJNoArguVisitor<R> v) {
      return v.visit(this);
   }
   public <A> void accept(visitor_piglet.GJVoidVisitor<A> v, A argu) {
      v.visit(this,argu);
   }
}


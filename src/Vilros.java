import connectK.CKPlayer;
import connectK.BoardModel;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Vilros extends CKPlayer {

	public static class PointAB {
		Point point;
		double alpha, beta;
		
		public PointAB(Point point, double alpha, double beta) {
			this.point = point;
			this.alpha = alpha;
			this.beta = beta;
		}
	}
	
	public static class childNodes {
		BoardModel state;
		Point point;
		double eval;
		
		public childNodes(BoardModel state, Point point, double eval) {
			this.state = state;
			this.point = point;
			this.eval = eval;
		}
	}
	
	public static class evalComparator implements Comparator<childNodes> {
		public int compare(childNodes x, childNodes y) {
			return Double.compare(x.eval, y.eval);
		}
	}
	
	public Vilros(byte player, BoardModel state) {
		super(player, state);
		teamName = "Vilros";
	}

	public boolean win(int score, BoardModel state) {
		return score >= state.kLength  ? true : false;
	}
	
	public int checkVert(BoardModel state) {
		int p1 = 0, p2 = 0;
		
		for( int i = 0; i < state.getWidth(); i++ ) {
			int pl1 = 0, pl2 = 0;
			for( int j = 0; j < state.getHeight(); j++ ) {
				byte space = state.getSpace(i, j);
				
				if(space == 1 && pl2 == 0)
					pl1++;
				else if(space == 1 && pl2 != 0) {
					pl2 = 0;
					pl1++;
				}
				else if(space == 2 && pl1 == 0)
					pl2++;
				else if(space == 2 && pl1 != 0) {
					pl1 = 0;
					pl2++;
				}
				else if( space == 0 ) {
					p1 += (pl1 * pl1);
					p2 += (pl2 * pl2);
					
					if(win(pl1,state)) p1 += 10000;
					if(win(pl2,state)) p2 += 10000;
					
					pl1 = 0;
					pl2 = 0;
				}
			}
			
			p1 += (pl1 * pl1);
			p2 += (pl2 * pl2);
			
			if(win(pl1, state)) p1 += 10000;
			if(win(pl2, state)) p2 += 10000;
		}
		
		return p1-p2;
	}
	
	public int checkHoriz(BoardModel state) {
		int p1 = 0, p2 = 0;
		
		for( int i = 0; i < state.getHeight(); i++ ) {
			int pl1 = 0, pl2 = 0;
			for( int j = 0; j < state.getWidth(); j++ ) {
				byte space = state.getSpace(j, i);
				
				if(space == 1 && pl2 == 0)
					pl1++;
				else if(space == 1 && pl2 != 0) {
					pl2 = 0;
					pl1++;
				}
				else if(space == 2 && pl1 == 0)
					pl2++;
				else if(space == 2 && pl1 != 0) {
					pl1 = 0;
					pl2++;
				}
				else if( space == 0 ) {
					p1 += (pl1 * pl1);
					p2 += (pl2 * pl2);
					
					if(win(pl1,state)) p1 += 10000;
					if(win(pl2,state)) p2 += 10000;
					
					pl1 = 0;
					pl2 = 0;
				}
				
			}
			
			p1 += (pl1 * pl1);
			p2 += (pl2 * pl2);
			
			if(win(pl1,state)) p1 += 10000;
			if(win(pl2,state)) p2 += 10000;
		}

		
		return p1 - p2;
	}
	
	public int checkRevHoriz(BoardModel state) {
		int p1 = 0, p2 = 0;
		
		for( int i = state.getHeight(); i < 0; i-- ) {
			int pl1 = 0, pl2 = 0;
			for( int j = state.getWidth(); j < 0; j-- ) {
				byte space = state.getSpace(j, i);
				
				if(space == 1 && pl2 == 0)
					pl1++;
				else if(space == 1 && pl2 != 0) {
					pl2 = 0;
					pl1++;
				}
				else if(space == 2 && pl1 == 0)
					pl2++;
				else if(space == 2 && pl1 != 0) {
					pl1 = 0;
					pl2++;
				}
				else if( space == 0 ) {
					p1 += (pl1 * pl1);
					p2 += (pl2 * pl2);
					
					if(win(pl1,state)) p1 += 10000;
					if(win(pl2,state)) p2 += 10000;
					
					pl1 = 0;
					pl2 = 0;
				}
				
			}
			
			p1 += (pl1 * pl1);
			p2 += (pl2 * pl2);
			
			if(win(pl1,state)) p1 += 10000;
			if(win(pl2,state)) p2 += 10000;
		}
		
		return p1 - p2;
	}
	
	public int checkDiagTopLeft(BoardModel state) {
		int p1 = 0, p2 = 0;
		
		for( int i = 0 + state.kLength - 1; i < state.getWidth(); i++ ) {
			int pl1 = 0, pl2 = 0, index = 0;
			for( int j = state.getHeight()-1; state.getHeight() - 1 - j < i + 1 && j >= 0; j-- ) {
				byte space = state.getSpace(i - index++, j);

				if(space == 1 && pl2 == 0)
					pl1++;
				else if(space == 1 && pl2 != 0) {
					pl2 = 0;
					pl1++;
				}
				else if(space == 2 && pl1 == 0)
					pl2++;
				else if(space == 2 && pl1 != 0) {
					pl1 = 0;
					pl2++;
				}
				else if( space == 0 ) {
					p1 += (pl1 * pl1);
					p2 += (pl2 * pl2);
					
					if(win(pl1,state)) p1 += 10000;
					if(win(pl2,state)) p2 += 10000;
					
					pl1 = 0;
					pl2 = 0;
				}
			}
			
			p1 += (pl1 * pl1);
			p2 += (pl2 * pl2);
			
			if(win(pl1,state)) p1 += 10000;
			if(win(pl2,state)) p2 += 10000;
		}
		
		return p1 - p2;
	}
	
	public int checkDiagBotLeft(BoardModel state) {
		int p1 = 0, p2 = 0;
		
		for( int i = 0 + state.kLength - 1; i < state.getWidth(); i++ ) {
			int pl1 = 0, pl2 = 0, index = 0;
			for( int j = 0;j < i + 1 && j < state.getHeight(); j++ ) {
				byte space = state.getSpace(i - index++, j);
				
				if(space == 1 && pl2 == 0)
					pl1++;
				else if(space == 1 && pl2 != 0) {
					pl2 = 0;
					pl1++;
				}
				else if(space == 2 && pl1 == 0)
					pl2++;
				else if(space == 2 && pl1 != 0) {
					pl1 = 0;
					pl2++;
				}
				else if( space == 0 ) {
					p1 += (pl1 * pl1);
					p2 += (pl2 * pl2);
					
					if(win(pl1,state)) p1 += 10000;
					if(win(pl2,state)) p2 += 10000;
					
					pl1 = 0;
					pl2 = 0;
				}
			}
			
			p1 += (pl1 * pl1);
			p2 += (pl2 * pl2);
			
			if(win(pl1,state)) p1 += 10000;
			if(win(pl2,state)) p2 += 10000;
		}
		
		return p1 - p2;
	}
	
	public int checkDiagTopRight(BoardModel state) {
		int p1 = 0, p2 = 0;
		
		for( int i = state.getWidth() - state.kLength - 1; i >= 0; i-- ) {
			int pl1 = 0, pl2 = 0, index = 0;
			for( int j = state.getHeight() - 1; state.getHeight() - 1 - j < i + 1; j-- ) {
				byte space = state.getSpace(i + ++index, j);
				
				if(space == 1 && pl2 == 0)
					pl1++;
				else if(space == 1 && pl2 != 0) {
					pl2 = 0;
					pl1++;
				}
				else if(space == 2 && pl1 == 0)
					pl2++;
				else if(space == 2 && pl1 != 0) {
					pl1 = 0;
					pl2++;
				}
				else if( space == 0 ) {
					p1 += (pl1 * pl1);
					p2 += (pl2 * pl2);
					
					if(win(pl1,state)) p1 += 10000;
					if(win(pl2,state)) p2 += 10000;
					
					pl1 = 0;
					pl2 = 0;
				}
			}
			
			p1 += (pl1 * pl1);
			p2 += (pl2 * pl2);
			
			if(win(pl1,state)) p1 += 10000;
			if(win(pl2,state)) p2 += 10000;
		}
		
		return p1 - p2;
	}
	
	public int checkDiagBotRight(BoardModel state) {
		int p1 = 0, p2 = 0;
		
		for( int i = state.getWidth() - state.kLength - 1; i >= 0; i-- ) {
			int pl1 = 0, pl2 = 0, index = 0;
			for( int j = 0; j < state.getWidth() - i - 2; j++ ) {
				byte space = state.getSpace(i + ++index, j);

				if(space == 1 && pl2 == 0)
					pl1++;
				else if(space == 1 && pl2 != 0) {
					pl2 = 0;
					pl1++;
				}
				else if(space == 2 && pl1 == 0)
					pl2++;
				else if(space == 2 && pl1 != 0) {
					pl1 = 0;
					pl2++;
				}
				else if( space == 0 ) {
					p1 += (pl1 * pl1);
					p2 += (pl2 * pl2);
					
					if(win(pl1,state)) p1 += 10000;
					if(win(pl2,state)) p2 += 10000;
					
					pl1 = 0;
					pl2 = 0;
				}
			}
			
			p1 += (pl1 * pl1);
			p2 += (pl2 * pl2);
			
			if(win(pl1,state)) p1 += 10000;
			if(win(pl2,state)) p2 += 10000;
		}
		
		return p1 - p2;
	}
	
	public int scoreBoard(BoardModel state) {
		int score = 0;
		score += checkRevHoriz(state);
		score += checkDiagBotRight(state);	
		score += checkVert(state);
		score += checkHoriz(state);
		score += checkDiagTopLeft(state);
		score += checkDiagBotLeft(state);
		score += checkDiagTopRight(state);		
		return score;
	}
	
	public int eval(BoardModel state) {
		/*
		 eval heuristic function: 1 in a row = 1pt, 2 in a row = 4pt,
		 3 in a row = 9pt, 4 in a row = 16pt, 5 in a row = 10000pt
		 
		 search the board in every direction and subtract the sum of
		 player 1's score by player 2's score
		 
		 if eval for player 1 - score will be positive
		 if eval for player 2 - score will be negative
		 */
		return scoreBoard(state);
	}
	
	public boolean rowVert(BoardModel state) {
		for( int i = 0; i < state.getWidth(); i++ ) {
			int pl1 = 0, pl2 = 0;
			for( int j = 0; j < state.getHeight(); j++ ) {
				byte space = state.getSpace(i, j);
				
				if(space == 1 && pl2 == 0)
					pl1++;
				else if(space == 1 && pl2 != 0) {
					pl2 = 0;
					pl1++;
				}
				else if(space == 2 && pl1 == 0)
					pl2++;
				else if(space == 2 && pl1 != 0) {
					pl1 = 0;
					pl2++;
				}

				if( pl1 == state.kLength - 1 || pl2 == state.kLength - 1 ) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean rowHoriz(BoardModel state) {
		for( int i = 0; i < state.getHeight(); i++ ) {
			int pl1 = 0, pl2 = 0;
			for( int j = 0; j < state.getWidth(); j++ ) {
				byte space = state.getSpace(j, i);
				
				if(space == 1 && pl2 == 0)
					pl1++;
				else if(space == 1 && pl2 != 0) {
					pl2 = 0;
					pl1++;
				}
				else if(space == 2 && pl1 == 0)
					pl2++;
				else if(space == 2 && pl1 != 0) {
					pl1 = 0;
					pl2++;
				}
				
				if( pl1 == state.kLength - 1 || pl2 == state.kLength - 1 ) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean rowDiagTopLeft(BoardModel state) {
		for( int i = 0 + state.kLength - 1; i < state.getWidth(); i++ ) {
			int pl1 = 0, pl2 = 0, index = 0;
			for( int j = state.getHeight()-1; state.getHeight() - 1 - j < i + 1 && j >= 0; j-- ) {
				byte space = state.getSpace(i - index++, j);

				if(space == 1 && pl2 == 0)
					pl1++;
				else if(space == 1 && pl2 != 0) {
					pl2 = 0;
					pl1++;
				}
				else if(space == 2 && pl1 == 0)
					pl2++;
				else if(space == 2 && pl1 != 0) {
					pl1 = 0;
					pl2++;
				}

				if( pl1 == state.kLength - 1 || pl2 == state.kLength - 1 ) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean rowDiagBotLeft(BoardModel state) {
		for( int i = 0 + state.kLength - 1; i < state.getWidth(); i++ ) {
			int pl1 = 0, pl2 = 0, index = 0;
			for( int j = 0;j < i + 1 && j < state.getHeight(); j++ ) {
				byte space = state.getSpace(i - index++, j);
				
				if(space == 1 && pl2 == 0)
					pl1++;
				else if(space == 1 && pl2 != 0) {
					pl2 = 0;
					pl1++;
				}
				else if(space == 2 && pl1 == 0)
					pl2++;
				else if(space == 2 && pl1 != 0) {
					pl1 = 0;
					pl2++;
				}

				if( pl1 == state.kLength - 1 || pl2 == state.kLength - 1 ) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean rowDiagTopRight(BoardModel state) {
		for( int i = state.getWidth() - state.kLength - 1; i >= 0; i-- ) {
			int pl1 = 0, pl2 = 0, index = 0;
			for( int j = state.getHeight() - 1; state.getHeight() - 1 - j < i + 1; j-- ) {
				byte space = state.getSpace(i + ++index, j);
				
				if(space == 1 && pl2 == 0)
					pl1++;
				else if(space == 1 && pl2 != 0) {
					pl2 = 0;
					pl1++;
				}
				else if(space == 2 && pl1 == 0)
					pl2++;
				else if(space == 2 && pl1 != 0) {
					pl1 = 0;
					pl2++;
				}

				if( pl1 == state.kLength - 1 || pl2 == state.kLength - 1 ) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean rowDiagBotRight(BoardModel state) {
		for( int i = state.getWidth() - state.kLength - 1; i >= 0; i-- ) {
			int pl1 = 0, pl2 = 0, index = 0;
			for( int j = 0; j < state.getWidth() - i - 2; j++ ) {
				byte space = state.getSpace(i + ++index, j);

				if(space == 1 && pl2 == 0)
					pl1++;
				else if(space == 1 && pl2 != 0) {
					pl2 = 0;
					pl1++;
				}
				else if(space == 2 && pl1 == 0)
					pl2++;
				else if(space == 2 && pl1 != 0) {
					pl1 = 0;
					pl2++;
				}
				
				if( pl1 == state.kLength - 1 || pl2 == state.kLength - 1 ) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean quiescence(BoardModel state) {
		boolean vert = rowVert(state),horiz = rowHoriz(state),tLeft = rowDiagTopLeft(state),
				bLeft = rowDiagBotLeft(state), tRight = rowDiagTopRight(state), bRight = rowDiagBotRight(state);
		return vert || horiz || tLeft || bLeft || tRight || bRight ? true : false;
	}
	
	public ArrayList<Point> getEmptySpaces(BoardModel state, int ideep) {
		ArrayList<Point> emptySpaces = new ArrayList<Point>();
		
		for( int i = 0; i < state.getWidth(); i++ )
			for( int j = 0; j < state.getHeight(); j++)
				if( state.getSpace(i, j) == 0 )
					emptySpaces.add( new Point(i,j) );
		return emptySpaces;
	}
	
	public PointAB minimaxAB(BoardModel state, int depth, byte player, int ideep, int deadline, long startTime, double alpha, double beta, int abon, int quiescence) {		
		ArrayList<Point> emptySpaces = getEmptySpaces(state, ideep);
		
		if( emptySpaces.size() == 0 )
			return new PointAB(null,alpha,beta);
		else if( state.winner() == player  )
			return new PointAB(state.lastMove,alpha,beta);
		
		PriorityQueue<childNodes> children = new PriorityQueue<childNodes>(emptySpaces.size(),new evalComparator());

		for( int i = 0; i < emptySpaces.size(); i++ ) {
			BoardModel s = state;
			s = s.placePiece(emptySpaces.get(i), player);
			
			double evalS = eval(s);
			children.add(new childNodes(s,emptySpaces.get(i),evalS));
		}
		
		Point bestMove = children.peek().point;
		PointAB bestPoint = new PointAB(bestMove,alpha,beta);
		byte nextPlayer = (byte) (player % 2 == 0 ? 1 : 2);
		double evalState = 0,a = 0,b = 0;
		
		if( abon == 0)
			evalState = eval(state);
		
		while( children.size() > 0 ) {
			if( System.currentTimeMillis() - startTime >= deadline )
				return bestPoint;
			
			childNodes node = children.remove();
			
			if( abon == 1) {
				if( player == 1 && depth < ideep && quiescence == 0) {
					a = node.eval;
					PointAB be = minimaxAB(node.state,depth+1,nextPlayer,ideep,deadline,startTime,a,beta,1,0);
					b = be.beta;
				}
				else if( player == 1 && depth >= ideep && quiescence == 0) {
					a = node.eval;
					b = beta;
					
					if(quiescence(state) && quiescence == 0) {
						PointAB be = minimaxAB(node.state,depth+1,nextPlayer,ideep,deadline,startTime,a,beta,1,1);
						b = be.beta;
					}
				}
				else if( player == 1 && depth > ideep && quiescence == 1 ) {
					a = node.eval;
					b = beta;
				}
				else if( player == 2 && depth < ideep && quiescence == 0) {
					b = node.eval;
					PointAB al = minimaxAB(node.state,depth+1,nextPlayer,ideep,deadline,startTime,alpha,b,1,0);
					a = al.alpha;
				}
				else if( player == 2 && depth >= ideep && quiescence == 0) {
					b = node.eval;
					a = alpha;
					
					if(quiescence(state) && quiescence == 0) {
						PointAB al = minimaxAB(node.state,depth+1,nextPlayer,ideep,deadline,startTime,alpha,b,1,1);
						a = al.alpha;
					}
				}
				else if( player == 2 && depth > ideep && quiescence == 1 ) {
					b = node.eval;
					a = alpha;
				}
				
				Point move = node.point;
			
				
				if( (player == 1 && a > alpha)  ) {
					bestPoint.point = move;
					alpha = a;
					if( beta != -99999 && alpha >= beta) {
						return new PointAB(move,alpha,beta);
					}
				}
				else if( (player == 2 && b < beta) ) {
					bestPoint.point = move;
					beta = b;
					if( alpha != 99999 && beta <= alpha) {
						return new PointAB(move,alpha,beta);
					}
					
				}
			}
			else { 
				Point move;
				if( depth >= ideep )
					move = node.point;
				else {
					PointAB mov = minimaxAB(node.state,depth+1,nextPlayer,ideep,deadline,startTime,alpha,beta,0,0);
					move = mov.point;
				}
				
				if( (player == 1 && node.eval > evalState) && depth >= ideep ) {
					evalState = node.eval;
					bestMove = move;
				}
				else if( (player == 1 && node.eval > evalState) && depth < ideep ) {
					evalState = node.eval;
					bestMove = node.state.lastMove;
				}
				else if( (player == 2 && node.eval < evalState) && depth >= ideep ) {
					evalState = node.eval;
					bestMove = move;
				}
				else if( (player == 2 && node.eval < evalState) && depth < ideep ) {
					evalState = node.eval;
					bestMove = node.state.lastMove;
				}

			}
		}
 		return bestPoint;
	}
	
	public Point returnMove(BoardModel state, int deadline, byte turn) {
		long startTime = System.currentTimeMillis();
		PointAB move = minimaxAB(state,0,turn,3,deadline,startTime,-99999,99999,1,0);
		return move.point;
	}
	
	@Override
	public Point getMove(BoardModel state) {
		return getMove(state,5000);
	}

	@Override
	public Point getMove(BoardModel state, int deadline) {
		Point p = state.getLastMove();
		byte b;
		
		if( p == null )
			b = 1;
		else {
			b = state.getSpace(state.getLastMove());
			if( b == 1 ) b = 2;
			else if( b == 2 ) b = 1;
		}
		return returnMove(state,deadline,b);
	}
}
public class studentAI extends Player {
    private int maxDepth;


    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public void move(BoardState state) {
        //System.out.println("max="+maxValue(state, maxDepth, 10, -1000, 1000));
        move = alphabetaSearch(state, maxDepth);
        //System.out.println("move = " + move);
    }

    public int alphabetaSearch(BoardState state, int maxDepth) {
      int currentDepth = maxDepth;
      int alpha = Integer.MIN_VALUE;
      int beta = Integer.MAX_VALUE;
      int move = 0;
      int temp = Integer.MIN_VALUE;

      for (int i = 0; i<6; i++){
        if (state.isLegalMove(1,i)){
          BoardState successor = state.applyMove(1,i);
          int mv = minValue(successor,maxDepth,currentDepth-1,alpha,beta);
          if (temp < mv){
            temp = mv;
            move = i;
          }
        }
      }
    	return move;
    }

    public int maxValue(BoardState state, int maxDepth, int currentDepth, int alpha, int beta) {

      if (currentDepth == 0 || state.status(2) != BoardState.GAME_NOT_OVER){
        return sbe(state);
      }

      currentDepth--;
      //System.out.println("currentDepth = " + currentDepth);
      int v = Integer.MIN_VALUE;
      for (int i = 0; i<6; i++){
        if (state.isLegalMove(1,i)){
          BoardState successor = state.applyMove(1,i);
          v = Math.max(v, minValue(successor,maxDepth,currentDepth,alpha,beta));
          if (v >= beta){
            return v;
          }
          alpha = Math.max(v, alpha);
        }
      }
    	return v;
    }

    public int minValue(BoardState state, int maxDepth, int currentDepth, int alpha, int beta) {

      if (currentDepth == 0 || state.status(1) != BoardState.GAME_NOT_OVER){
        return sbe(state);
      }

      currentDepth--;
      int v = Integer.MAX_VALUE;
      for (int i = 0; i<6; i++){
        if (state.isLegalMove(2,i)){
          BoardState successor = state.applyMove(2,i);
          v = Math.min(v, maxValue(successor,maxDepth,currentDepth,alpha,beta));
          if (v <= alpha){
            return v;
          }
          beta = Math.min(v, beta);
        }
      }
    	return v;
    }

    public int sbe(BoardState state){

    	return state.score[0] - state.score[1];
    }


}

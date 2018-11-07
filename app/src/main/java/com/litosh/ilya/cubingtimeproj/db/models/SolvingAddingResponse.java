package com.litosh.ilya.cubingtimeproj.db.models;

/**
 * SolvingAddingResponse
 *
 * @author Ilya Litosh
 */
public class SolvingAddingResponse {

    private long mTimeDbId;
    private long mSolveDbId;

    public long getTimeDbId() {
        return mTimeDbId;
    }

    public void setTimeDbId(long mTimeDbId) {
        this.mTimeDbId = mTimeDbId;
    }

    public long getSolveDbId() {
        return mSolveDbId;
    }

    public void setSolveDbId(long mSolveDbId) {
        this.mSolveDbId = mSolveDbId;
    }

    public SolvingAddingResponseBuilder builder() {
        return new SolvingAddingResponseBuilder(this);
    }

    /**
     * SolvingAddingResponseBuilder
     *
     * @author Ilya Litosh
     */
    public class SolvingAddingResponseBuilder {

        private SolvingAddingResponse mSolvingAddingResponse;

        private SolvingAddingResponseBuilder(SolvingAddingResponse solvingAddingResponse) {
            mSolvingAddingResponse = solvingAddingResponse;
        }

        public SolvingAddingResponseBuilder timeId(long timeId) {
            mSolvingAddingResponse.setTimeDbId(timeId);
            return this;
        }

        public SolvingAddingResponseBuilder solveId(long solveId) {
            mSolvingAddingResponse.setSolveDbId(solveId);
            return this;
        }

        public SolvingAddingResponse build() {
            return mSolvingAddingResponse;
        }

    }

}

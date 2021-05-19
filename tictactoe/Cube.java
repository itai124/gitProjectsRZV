import java.io.Serializable;

public class Cube implements Serializable {
  private int row;
  private int col;
  private char sign;
  static final long serialVersionUID = 1;

  public Cube() {
    this.setRow(0);
    this.setCol(0);
    this.setSign(' ');
  }

  public Cube(int row, int col, char sign) {
    this.setRow(row);
    this.setCol(col);
    this.setSign(sign);
  }

  public Cube(int row, int col) {
    this.setRow(row);
    this.setCol(col);
    this.setSign(' ');
  }

  public int getRow() {
    return this.row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public int getCol() {
    return this.col;
  }

  public void setCol(int col) {
    this.col = col;
  }

  public char getSign() {
    return this.sign;
  }

  public void setSign(char sign) {
    this.sign=sign;
  }
}

package App.Multicenter.GUI;

import java.awt.*;

public class PersonalSpaceView extends Container {
    //Atributtes
    Header header;
    Board board;
    String name;

    //Constructor
    public PersonalSpaceView(Header header, Board board, String name) {
        this.header = header;
        this.board = board;
        this.name = name;

        setPreferredSize(new Dimension(250, 250));
        setLayout(new BorderLayout());

        add(header, BorderLayout.NORTH);
        add(board, BorderLayout.CENTER);

        setVisible(true);
    }

    //Methods

    /**
     * Devuelve el objeto Header actual del espacio personal
     *
     * @return header Header
     */
    public Header getHeader() {
        return header;
    }

    /**
     * Modifica el objeto Header actual del espacio personal
     *
     * @param newHeader Nueva cabecera para el espacio personal
     */
    public void setHeader(Header newHeader) {
        header = newHeader;
    }

    /**
     * Devuelve el objeto Board actual del espacio personal
     *
     * @return board Board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Modifica el objeto Board actual del espacio personal
     *
     * @param newBoard Nuevo tablón para el espacio personal
     */
    public void setBoard(Board newBoard) {
        board = newBoard;
    }

    public String toString() {
        return "psv[" + header.title + "]";
    }

}

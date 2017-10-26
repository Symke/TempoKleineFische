package data;

import java.util.ArrayList;
import data.Dice.Color;
import java.util.List;
import java.util.Objects;

/***
 * 
 * @author martina.kettenbach
 *
 */
public class RiverPart {
	private List<Figure> figures = new ArrayList<Figure>();

	public List<Figure> getFigures() {
		return figures;
	}

	public int getFigureIdxByColor(Color color) {
		// TODO use lamda
		for (int i = 0; i < figures.size(); i++) {
			if (figures.get(i).getColor() == color) {
				return i;
			}
		}

		return -1;
	}

	public Figure getFigureByIndex(int index) {
		return figures.get(index);
	}

	public void addFigure(Figure newFigure) {
		figures.add(newFigure);
	}

	public void appendFigureLst(List<Figure> newFigureLst) {
		figures.addAll(newFigureLst);
	}

	public void removeFigure(int index) {
		figures.remove(index);
	}

	@Override
	public boolean equals(Object obj) {

		if (!super.equals(obj))
			return false;

		RiverPart target = (RiverPart) obj;
		if (this.hashCode() != target.hashCode()) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(figures);
	}

	@Override
	public String toString() {
		return "{Figures: " + figures.toString() + "}";
	}

}

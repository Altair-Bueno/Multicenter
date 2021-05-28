package App.Multicenter.Space;

import App.Multicenter.Widget.Data.WidgetData;

import java.io.Serializable;
import java.util.Set;

public class PersonalSpaceData implements Serializable {
    public Set<WidgetData> widgetData;
    public String id;
    public String folderPath;
}

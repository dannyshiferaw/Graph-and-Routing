import com.dshiferaw.Graph_Routing.Import_Data.DEdge;
import com.dshiferaw.Graph_Routing.Import_Data.DNode;
import com.dshiferaw.Graph_Routing.Import_Data.SFRoadService;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

/** This file tests if SFRoadService class
 * reads data accurately
 */
public class TestSFRoadService {

    @Test
    public void testLoad() {

        Map<String, Object> setOfData = SFRoadService.load();

        List<DNode> nodes = (List<DNode>) setOfData.get("Nodes");
        List<DEdge> edges = (List<DEdge>) setOfData.get("Edges");

        for (DNode node: nodes) System.out.println(node.getNodeId());
        for (DEdge edge: edges) System.out.println(edge.getEdgeId());


    }
}

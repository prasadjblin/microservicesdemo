/*
 * Graph.java
 *
 * Created on 3 August 2007, 07:41
 *
 * Copyright 2007 Mateusz Wenus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.sourceforge.code2uml.graph;

import java.util.Collection;

/**
 * Defines methods used to get information about a graph of classes/interfaces/enums
 * and relationships between them that can be drawn as UML class diagram.
 *
 * @author Mateusz Wenus
 */
public interface Graph {
    
    /**
     * Returns nodes of this graph. 
     *
     * @return nodes of this graph
     */
    public Collection<NodeComponent> getNodes();
   
    /**
     * Returns width of this graph.
     * 
     * @return width of this graph
     */
    public int getWidth();
    
    /**
     * Returns height of this graph. 
     *
     * @return height of this graph
     */
    public int getHeight();
    
}
